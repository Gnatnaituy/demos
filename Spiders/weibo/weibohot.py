import requests
from bs4 import BeautifulSoup
import pymysql


hot_raw_text = requests.get("http://s.weibo.com/top/summary?Refer=top_hot&topnav=1&wvr=6")
soup = BeautifulSoup(hot_raw_text.text, 'html.parser')

