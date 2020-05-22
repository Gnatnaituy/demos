#!usr/bin/env python
# -*- coding:utf-8 -*-

from flask_pagedown.fields import PageDownField
from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField, TextAreaField, BooleanField, SelectField, PasswordField
from wtforms.validators import DataRequired, Length, Email, Regexp, ValidationError, EqualTo
from app_demo.models import Role, User


# 登录
class LoginForm(FlaskForm):
    email = StringField('邮箱', validators=[DataRequired(), Length(1, 64), Email()])
    password = PasswordField('密码', validators=[DataRequired()])
    remember_me = BooleanField('记住我')
    submit_login = SubmitField('登录')


# 注册
class RegistrationForm(FlaskForm):
    email = StringField('', validators=[DataRequired(), Length(1, 64), Email()])
    username = StringField('', validators=[DataRequired(), Length(1, 64), Regexp('^[A-Za-z][A-Za-z0-9_.]*$',
                           0, '用户名只能包含数字，字母和下划线')])
    password = PasswordField('', validators=[DataRequired(), EqualTo('password2', message='密码不一致')])
    password2 = PasswordField('', validators=[DataRequired()])
    submit_register = SubmitField('注册')

    def validate_email(self, field):
        if User.query.filter_by(email=field.data).first():
            raise ValidationError('邮箱已被注册')

    def validate_username(self, field):
        if User.query.filter_by(username=field.data).first():
            raise ValidationError('用户名已被注册')


# 编辑资料
class EditProfileForm(FlaskForm):
    name = StringField('昵称', validators=[Length(0, 64)])
    location = StringField('地址', validators=[Length(0, 64)])
    about_me = TextAreaField('签名')
    submit = SubmitField('保存')


# 编辑资料 -- 管理员
class EditProfileAdminForm(FlaskForm):
    email = StringField('邮箱', validators=[DataRequired(), Length(1, 64), Email()])
    username = StringField('用户名', validators=[DataRequired(), Length(1, 64),
                           Regexp('^[A-Za-z][A-Za-z0-9_.]*$', 0, '用户名只能有字母，数字，点和下划线')])
    confirmed = BooleanField('')
    role = SelectField('权限', coerce=int)
    name = StringField('昵称', validators=[Length(0, 64)])
    location = StringField('地址', validators=[Length(0, 64)])
    about_me = TextAreaField('签名')
    submit = SubmitField('提交')

    def __init__(self, user, *args, **kwargs):
        super(EditProfileAdminForm, self).__init__(*args, **kwargs)
        self.role.choices = [(role.id, role.name) for role in Role.query.order_by(Role.name).all()]
        self.user = user

    def validate_email(self, field):
        if field.data != self.user.email and User.query.filter_by(email=field.data).first():
            raise ValidationError('邮箱已注册')

    def validate_username(self, field):
        if field.data != self.user.username and User.query.filter_by(username=field.data).first():
            raise ValidationError('用户名已被注册')


# 发表推文
class PostForm(FlaskForm):
    title = StringField('标题', validators=[DataRequired('请输入标题'), Length(0, 128)])
    body = PageDownField('内容', validators=[DataRequired('内容为空')])
    submit = SubmitField('发表')


# 评论
class CommentForm(FlaskForm):
    body = TextAreaField('', validators=[DataRequired()])
    submit = SubmitField('评论')
