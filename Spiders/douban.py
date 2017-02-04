#!/usr/bin/env python
# -*- coding: utf-8 -*-


import requests
from bs4 import BeautifulSoup


r = requests.get('https://book.douban.com/tag/%E7%BC%96%E7%A8%8B')
html_raw = r.text
soup = BeautifulSoup(html_raw, 'html.parser')
# for link in soup.find_all('a'):
#     print(link.get('href'))
# print(soup.get_text())

book = soup.find_all('#subject_list > ul > li:nth-child > div.info > h2 > a')
for book_item in book:
    print(book_item)
