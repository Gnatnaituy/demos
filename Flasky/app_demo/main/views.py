#!usr/bin/env python
# -*- coding:utf-8 -*-
from flask import render_template, redirect, url_for, flash, request
from flask_login import login_user, login_required, current_user
from werkzeug.exceptions import abort

from . import main
from app_demo.decorators import admin_required
from app_demo.email import send_email
from app_demo.models import User, Permission, Role, Post, Comment
from .forms import EditProfileForm, EditProfileAdminForm, PostForm, CommentForm
from .forms import LoginForm, RegistrationForm


@main.route('/', methods=['GET', 'POST'])
def index():
    # 博文视图
    form_post = PostForm()
    page = request.args.get('page', 1, type=int)
    pagination = Post.query.order_by(Post.timestamp.desc()).paginate(page, per_page=10, error_out=False)
    posts = pagination.items
    # 登录
    form_login = LoginForm()
    if form_login.validate_on_submit():
        user = User.query.filter_by(email=form_login.email.data).first()
        if user is not None and user.verify_password(form_login.password.data):
            login_user(user)
            return redirect(request.args.get('next') or url_for('.index'))
        flash('用户名或密码错误')
    # 注册
    form_register = RegistrationForm()
    if form_register.validate_on_submit():
        user = User(email=form_register.email.data,
                    username=form_register.username.data,
                    password=form_register.password.data)
        db.session.add(user)
        db.session.commit()
        token = user.generate_confirmation_token()
        send_email(user.email, '请确认你的账户', 'auth/email/confirm',
                   user=current_user, token=token)
        flash('验证邮件已经发往你的邮箱。')
        return redirect(url_for('.index'))

    return render_template('index.html', form_post=form_post,
                           form_login=form_login, form_register=form_register,
                           posts=posts, pagination=pagination)


@main.route('/user/<username>')
def user(username):
    user = User.query.filter_by(username=username).first()
    if user is None:
        abort(404)
    posts = user.posts.order_by(Post.timestamp.desc()).all()
    return render_template('user.html', user=user, posts=posts)


@main.route('/edit-profile', methods=['GET', 'POST'])
@login_required
def edit_profile():
    form = EditProfileForm()
    if form.validate_on_submit():
        current_user.name = form.name.data
        current_user.location = form.location.data
        current_user.about_me = form.about_me.data
        db.session.add(current_user)
        flash('修改已保存')
        return redirect(url_for('.user', username=current_user.username))
    form.name.data = current_user.name
    form.location.data = current_user.location
    form.about_me.data = current_user.about_me
    return render_template('edit_profile.html', form=form)


@main.route('/edit-profile/<user_id>', methods=['GET', 'POST'])
@login_required
@admin_required
def edit_profile_admin(user_id):
    user = User.query.get_or_404(user_id)
    form = EditProfileAdminForm(user=user)
    if form.validate_on_submit():
        user.email = form.email.data
        user.username = form.username.data
        user.confirmed = form.confirmed.data
        user.role = Role.query.get(form.role.data)
        user.name = form.name.data
        user.location = form.location.data
        user.about_me = form.about_me.data
        flash('修改已保存')
        return redirect(url_for('.user', username=current_user.username))
    form.email.data = user.email
    form.username.data = user.username
    form.confirmed.data = user.confirmed
    form.role.data = user.role
    form.name.data = user.name
    form.location.data = user.location
    form.about_me.data = user.about_me
    return render_template('edit_profile.html', form=form, user=user)


@main.route('/post/<user_id>', methods=['GET', 'POST'])
def post(user_id):
    post = Post.query.get_or_404(user_id)
    form = CommentForm()
    if form.validate_on_submit():
        comment = Comment(body=form.body.data, post=post, author=current_user._get_current_object())
        db.session.add(comment)
        return redirect(url_for('.post', id=post.id, page=-1))
    page = request.args.get('page', 1, type=int)
    if page == -1:
        page = (post.comments.count() - 1) // 10 + 1
    pagination = post.comments.order_by(Comment.timestamp.asc()).paginate(
        page, per_page=10, error_out=False)
    comments = pagination.items
    return render_template('post.html', posts=[post], form=form, comments=comments, pagination=pagination)


@main.route('/edit/<user_id>', methods=['GET', 'POST'])
@login_required
def edit(user_id):
    post = Post.query.get_or_404(user_id)
    if current_user != post.author and not current_user.can(Permission.ADMINISTER):
        abort(403)
    form = PostForm()
    if form.validate_on_submit():
        post.title = form.title.data
        post.body = form.body.data
        db.session.add(post)
        flash('编辑已保存！')
        return redirect(url_for('.post', id=post.id))
    form.body.data = post.body
    form.title.data = post.title
    return render_template('edit_post.html', form=form)


@main.route('/write', methods=['GET', 'POST'])
@login_required
def write():
    form = PostForm()
    if current_user.can(Permission.WRITE_ARTICLES) and form.validate_on_submit():
        post = Post(title=form.title.data, body=form.body.data,
                    author=current_user._get_current_object())
        db.session.add(post)
        flash('发表成功！')
        return redirect(url_for('.index'))
    return render_template('write.html', form=form, file_url=file_url)
