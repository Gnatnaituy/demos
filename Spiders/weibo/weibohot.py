import requests
from bs4 import BeautifulSoup
import pymysql


connect = pymysql.connect(
    host='localhost', user='root', password='5523', port=3306,
    database='spiders', charset='utf8')
sql = 'insert into `weibo_hots` (`Words`, `Counts`) values (%s,%s)'

headers = {
    'user-agent': 'Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X)' +
    'AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 ' +
    'Safari/601.1'} 
url = "http://s.weibo.com/top/summary?Refer=top_hot&topnav=1&wvr=6"

hot_raw_text = requests.get(url, headers=headers)
soup = BeautifulSoup(hot_raw_text.text, 'html.parser')
tags = soup.find_all('li')


if __name__ == "__main__":
    try:
        with connect.cursor() as cursor:
            for tag in tags:
                count_raw = tag.a.span.em.text
                word_raw = tag.a.span.text
                word = word_raw.replace(count_raw, '')
                count = int(count_raw)
                cursor.execute(sql, (word, count))
                connect.commit()
    finally:
        connect.close()
