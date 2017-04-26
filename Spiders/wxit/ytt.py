import requests
import re
from bs4 import BeautifulSoup


# 自己的id
my_id = 421
# 登陆用的cookie
after_cookie='PHPSESSID=s6hs2ef0gpifn04sn5karu1jn1; HicourseSESSIONID=r6558s7ui6dkvud9mkpa3tseu3; b816448737fd5b8ac832b94a869ec7dc_13405=f8eeiX8LVeiT1S5yiM7eSqMovLqlcD5jYlxag6WRMvthys2eyhjA; c3cf13b9a8b9ecef0a16f55f764695a9_13405=97d8ypdQ1s%2FCujORTOh4JguJsWf%2F9ffESxdj4XAkbGu5nFd63V87eujPN5jAWtxB; 07bf2ad6a07c3199a9e80f31335ef267=a799uHUCqanp70iJfTVWn14w6vOyDtWnFJe86tcQRorsPUPHlICUSSsYKsFsWfEJQzGV%2BWOKO72ppWF4%2BIa8vlf3mN7MXbE2OMtFwDQfGpHK6tezDgdpfVLpm9YNLwwR%2BmNRlbnv2fALMQ%2F%2B3QWI0q0helGGZX1oU6lv6b0xerEZPDPJb7zUECokM%2FrRLVqzGPl9qFUkckKDmzb%2Fy7sun1LxAPRUkdo7ZnY8EImiu%2FtgS8dfTqpe569xSJu53%2BBA%2FJy6MASKE5A; b816448737fd5b8ac832b94a869ec7dc_13413=a799uHUCqanp70iJfTVVnVxg7fLoCYCjRsG7uIdZVNK%2FMknRiIGW'

cookies = {}
for line in after_cookie.split(';'):
    key, value = line.split('=', 1) # 1代表只分一次
    cookies[key] = value


if __name__ == '__main__':
    course_urls = []    
    for i in range(14240, 14396):
        tags = []
        course_url = 'http://wxit.org/portal/coursecontent/index/treeid/' +\
             str(i) + '/id/' + str(my_id) + '.html'
        r = requests.get(course_url, cookies=cookies)
        soup = BeautifulSoup(r.content, 'html.parser')
        for tag_raw in soup.find_all(href= \
        re.compile("/portal/coursecontent/index/treeid/\d{5}/lessonid/\d{5}/id/\d{3}.html")):
            tags.append(str(tag_raw))
        for tag in tags:
            if 'xueguo_col' in tag:
                print(tag + '>>>>>>已学过')
            elif not 'xueguo_col' in tag:
                requests.get('http://wxit.org/portal/coursecontent/index/treeid/' + str(i)\
                     + '/lessonid/' + tag[83:88] + '/id/' + str(my_id) + '.html', cookies=cookies)
                print('http://wxit.org/portal/coursecontent/index/treeid/' + str(i) + '/lessonid/' +\
                    tag[83:88] + '/id/' + str(my_id) + '.html' + '>>>>>>正在学习')
            else:
                print('>>>>>>>>出错了<<<<<<<<<<')


