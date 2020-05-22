from django.shortcuts import render, redirect, get_object_or_404
from django.core.exceptions import ObjectDoesNotExist
from django.http import JsonResponse
from django.views.decorators.cache import cache_page

from .models import Article, Comment, Poll
from .forms import CommentForm
import markdown2


def index(request):
    latest_article_list = Article.objects.query_by_time()
    context = {'latest_article_list': latest_article_list}
    return render(request, 'blog/index.html', context)


def article(request, article_id):
    """
    try:   # since visitor input a url with invalid id
        article = Article.objects.get(pk=article_id)  # pk???
    except Article.DoesNotExist:
        raise Http404("Article does not exist")
    """
    article = get_object_or_404(Article, id=article_id)
    content = markdown2.markdown(
        article.content, extras=[
                                    "code-friendly",
                                    "fenced-code-blocks",
                                    "header-ids",
                                    "toc",
                                    "metadata"
                                    ])
    watch = article.watch_num
    comments = article.comment_set.all
    commentform = CommentForm()

    article.watch_num += 1
    article.save()      # Increase watch times

    return render(request, 'blog/article_page.html', {
        'article': article,
        'commentform': commentform,
        'content': content,
        'comments': comments,
        'watches': watch
    })


def comment(request, article_id):
    form = CommentForm(request.POST)
    url = urlparse.urljoin('/blog/', article_id)
    if form.is_valid():
        user = request.user
        article = Article.objects.get(id=article_id)
        new_comment = form.cleaned_data['comment']
        c = Comment(content=new_comment, article_id=article_id)  # have tested by shell
        c.user = user
        c.save()
        article.comment_num += 1
        article.save()
    return redirect(url)


def poll(request, article_id):
    logged_user = request.user
    article = Article.objects.get(id=article_id)
    polls = logged_user.poll_set.all()
    articles = []
    for poll in polls:
        articles.append(poll.article)

    if article in articles:
        url = urlparse.urljoin('/blog/', article_id)
        return redirect(url)
    else:
        article.poll_num += 1
        article.save()
        poll = Poll(user=logged_user, article=article)
        poll.save()
        return redirect('/blog/')


def about_me(request):
    return render(request, 'blog/about_me.html')
