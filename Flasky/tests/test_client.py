#!usr/bin/env python
# -*- coding:utf-8 -*-
import re
import unittest

from flask import url_for

from app import create_app, db
from app.models import User, Role


class FlaskClientTestCase(unittest.TestCase):
    def setUp(self):
        self.app = create_app('testing')
        self.app_context = self.app.app_context()
        self.app_context.push()
        db.create_all()
        Role.insert_roles()
        self.client = self.app.test_client(use_cookies=True)

    def tearDown(self):
        db.session.remove()
        db.drop_all()
        self.app_context.pop()

    def test_home_page(self):
        response = self.client.get(url_for('main.index'))
        self.assertTrue('游客' in response.get_data(as_text=True))

    def test_register_and_login(self):
        # register a now account
        response = self.client.post(url_for('auth.register'), data={
            'email': 'aaaa@aaa.com',
            'username': 'aaaaaa',
            'password': 'aaaaaa',
            'password2': 'aaaaaa'
        })
        self.assertTrue(response.status_code == 302)

        # login with new account
        response = self.client.post(url_for('auth.login'), data={
            'email': 'aaaa@aaa.com',
            'password': 'aaaaaa'
        }, follow_redirects=True)
        data = response.get_data(as_text=True)
        self.assertTrue(re.search('你好,\s+aaaaaa', data))
        self.assertTrue('您还没有验证您的账户' in data)

        # send a confirm token
        user = User.query.filter_by(email='aaaa@aaa.com').first()
        token = user.generate_confirmation_token()
        response = self.client.get(url_for('auth.confirm', token=token), follow_redirects=True)
        data = response.get_data(as_text=True)
        self.assertTrue('您已验证完毕，谢谢!' in data)

        # log out
        response = self.client.get(url_for('auth.logout'), follow_redirects=True)
        data = response.get_data(as_text=True)
        self.assertTrue('你已登出' in data)
