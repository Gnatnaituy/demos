#!usr/bin/env python
# -*- coding:utf-8 -*-

from flask import Blueprint

main = Blueprint('main', __name__)

from . import views, errors


@main.app_context_processor
def inject_permission():
    from app_demo.models import Permission
    return dict(Permission=Permission)
