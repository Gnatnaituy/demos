#!usr/bin/env python
# -*- coding:utf-8 -*-


from flask import Flask
from flask_bootstrap import Bootstrap
from flask_moment import Moment
from flask_admin import Admin
from flask_debugtoolbar import DebugToolbarExtension

from config import config


bootstrap = Bootstrap()
moment = Moment()
toolbar = DebugToolbarExtension()
admin = Admin(name='Blog')


def create_blog(config_name):

    # init app
    blog = Flask(__name__)
    blog.config.from_object(config[config_name])
    config[config_name].init_app(blog)

    from app_blog.models import db
    db.init_app(blog)

    bootstrap.init_app(blog)
    moment.init_app(blog)
    toolbar.init_app(blog)

    # background administrator
    from flask_admin.contrib.sqla import ModelView
    from app_blog.models import Article
    admin.init_app(blog)
    admin.add_view(ModelView(Article, db.session))

    # Blueprint
    from .main import main as main_blueprint
    blog.register_blueprint(main_blueprint)

    return blog
