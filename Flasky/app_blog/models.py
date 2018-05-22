from datetime import datetime

from markdown import markdown
import bleach
from flask import url_for
from flask_sqlalchemy import SQLAlchemy
from wtforms import ValidationError


db = SQLAlchemy()


class Article(db.Model):

    __tablename__ = 'article'

    id = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.Text)
    body = db.Column(db.Text)
    body_markdown = db.Column(db.Text)
    vote = db.Column(db.Integer, default=0)
    watch = db.Column(db.Integer, default=0)
    timestamp = db.Column(db.DateTime, index=True,
                          default=datetime.utcnow)

    @staticmethod
    def on_changed_body(target, value, oldvalue, initiator):
        allowed_tags = ['a', 'abbr', 'acronym', 'b', 'blockquote', 'code',
                        'em', 'i', 'li', 'ol', 'pre', 'strong', 'ul',
                        'h1', 'h2', 'h3', 'p', 'img']
        target.body_html = bleach.linkify(bleach.clean(
            markdown(value, output_format='html'),
            tags=allowed_tags, strip=True))

    def to_json(self):
        json_post = {
            'url': url_for('api.get_post', id=self.id, _external=True),
            'title': self.title,
            'body': self.body,
            'body_html': self.body_html,
            'timestamp': self.timestamp,
            'author': url_for('api.get_user', id=self.author_id,
                              _external=True),
            'comments': url_for('api.get_post_comments', id=self.id,
                                _external=True),
            'comment_count': self.comments.count()
        }
        return json_post

    @staticmethod
    def from_json(json_post):
        body = json_post.get('body')
        if body is None or body == '':
            raise ValidationError('post does not have a body')
        return Article(body=body)


db.event.listen(Article.body, 'set', Article.on_changed_body)
