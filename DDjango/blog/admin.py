from django.contrib import admin
from django.db import models
from django import forms

from .models import Article, Comment


class CommentAdmin(admin.ModelAdmin):
    list_display = ('article_id', 'pub_date', 'content')


class ArticleAdmin(admin.ModelAdmin):
    formfield_overrides = {
        models.TextField: {'widget': forms.Textarea(
            attrs={'rows': 41,
                   'cols': 100
                   })},
    }
    list_display = ('title', 'pub_time', 'poll_num')


admin.site.register(Comment, CommentAdmin)
admin.site.register(Article, ArticleAdmin)
