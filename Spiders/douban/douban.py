#!/usr/bin/env python
# -*- coding: utf-8 -*-


import requests
from bs4 import BeautifulSoup
import pymysql


def get_max_page(url):
    """返回最大页码数."""
    content_raw = requests.get(url).text
    soup = BeautifulSoup(content_raw, 'html.parser')
    # 从pagination中获取最大页码
    max_page = soup.select('.pagination')[0].contents[0].contents[-2].text
    return int(max_page)


def get_price(content):
    """如果价格为 免费 未定价 或者未标价格 返回 None.
    如果有打折 返回打折后的价格.
    """
    if content.select('.price-tag') == [] \
            or content.select('.price-tag')[0].text == '免费' \
            or content.select('.price-tag')[0].text == '未定价':
        return None
    elif len(content.select('.price-tag')[0].contents) == 2:
        return float(content.select('.price-tag')[0].contents[1].text[:-1])
    else:
        return float(content.select('.price-tag')[0].text[:-1])


def get_book_info(url):
    content_raw = requests.get(url).text
    soup = BeautifulSoup(content_raw, 'html.parser')
    for content in soup.select('.info'):
        title = content.select('.title')[0].text
        author = None if content.select('.author-item') == [] \
            else content.select('.author-item')[0].text
        price = get_price(content)
        category = None if content.select('.category') == [] \
            else content.select('.category')[0].text[3:]
        rating = None if content.select('.rating-average') == [] \
            else float(content.select('.rating-average')[0].text)
        eveluate_nums = None if content.select('.ratings-link') == [] \
            else int(content.select('.ratings-link')[0].text[:-3])
        desc = None if content.select('.article-desc-brief') == [] \
            else content.select('.article-desc-brief')[0].text
        print('Downloading page -->', url)
        cursor.execute(sql, (title, author, price, category,
                             rating, eveluate_nums, desc))
        connect.commit()


connect = pymysql.connect(
    host='localhost', user='root', password='5523', port=3306,
    database='spiders', charset='utf8'
)
sql = 'insert into `douban_books`(`title`, `author`, `price`, `category`, `rating`, \
      `eveluate_nums`, `description`) values (%s,%s,%s,%s,%s,%s,%s)'


if __name__ == '__main__':
    search = input('请输入书籍关键字:')
    try:
        with connect.cursor() as cursor:
            url_prefix = 'https://read.douban.com/search?q=' + \
                str(search) + '&start='
            urls = []
            for i in range(0, get_max_page(url_prefix)):
                urls.append(url_prefix + str(i * 10))
            for url in urls:
                get_book_info(url)
    finally:
        connect.close()
