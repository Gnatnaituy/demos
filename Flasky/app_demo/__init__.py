#!usr/bin/env python
# -*- coding:utf-8 -*-


from flask import Flask
from flask_login import LoginManager
from flask_mail import Mail
from flask_moment import Moment
from flask_pagedown import PageDown
from flask_wtf.csrf import CSRFProtect
from flask_bootstrap import Bootstrap

from config import config

login_manager = LoginManager()
login_manager.session_protection = 'strong'
login_manager.login_view = 'auth.login'

mail = Mail()
moment = Moment()
pagedown = PageDown()
csrf = CSRFProtect()
bootstrap = Bootstrap()


def create_demo(config_name):
    demo = Flask(__name__)
    demo.config.from_object(config[config_name])
    config[config_name].init_app(demo)

    from app_demo.models import db
    db.init_app(demo)

    login_manager.init_app(demo)
    mail.init_app(demo)
    moment.init_app(demo)
    pagedown.init_app(demo)
    csrf.init_app(demo)
    bootstrap.init_app(demo)

    from .main import main as main_blueprint
    demo.register_blueprint(main_blueprint)

    from .auth import auth as auth_blueprint
    demo.register_blueprint(auth_blueprint, url_prefix='/auth')

    from .api_1_0 import api as api_1_0_blueprint
    demo.register_blueprint(api_1_0_blueprint, url_prefix='/api/v1.0')

    return demo
