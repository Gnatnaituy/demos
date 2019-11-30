#!/usr/bin/env python
# -*- coding: utf-8 -*-

from django.conf.urls import url
from . import views


urlpatterns = [
    url(r'^$', views.index, name='index'),
    url(r'^(?P<article_id>[0-9]+)/comment/$', views.comment, name='comment'),
    url(r'^(?P<article_id>[0-9]+)/poll/$', views.poll, name='poll'),
    url(r'^(?P<article_id>[0-9]+)/$', views.article, name='article'),
    url('about_me/$', views.about_me, name='about_me'),
]
