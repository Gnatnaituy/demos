# usr/bin/env python
# -*- coding: utf-8 -*-

from django.db import models


class ArticleManager(models.Manager):

    def query_by_polls(self):
        query = self.get_queryset().order_by('poll_num')
        return query

    def query_by_time(self):
        query = self.get_queryset().order_by('-pub_time')
        return query


class Article(models.Model):
    title = models.CharField(max_length=128)
    content = models.TextField()
    pub_time = models.DateTimeField(auto_now_add=True, editable=True)
    update_time = models.DateTimeField(auto_now=True, null=True)
    poll_num = models.IntegerField(default=0)
    comment_num = models.IntegerField(default=0)
    watch_num = models.IntegerField(default=0)
    objects = ArticleManager()

    def __str__(self):
        return self.title

    class Meta:
        verbose_name = 'article'
        verbose_name_plural = 'article'


class Comment(models.Model):
    article = models.ForeignKey(Article, null=True)
    content = models.TextField()
    pub_date = models.DateTimeField(auto_now_add=True, editable=True)

    def __str__(self):
        return self.content


class Poll(models.Model):
    article = models.ForeignKey(Article, null=True)
    comment = models.ForeignKey(Comment, null=True)
