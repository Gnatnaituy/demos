from flask import render_template
from . import main
from app_blog.models import Article


# Home page --- Articles
@main.route('/', methods=['POST', 'GET'])
def index():
    articles = Article.query.order_by(Article.timestamp.desc())
    return render_template('index.html', articles=articles)


# Article page
@main.route('/article/<int:article_id>', methods=['GET', 'POST'])
def article(article_id):
    the_article = Article.query.get_or_404(article_id)
    return render_template('article.html', the_article=the_article)


# About me page
@main.route('/me')
def me():
    return render_template('me.html')
