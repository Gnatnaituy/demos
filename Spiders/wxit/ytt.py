import requests
import re
from bs4 import BeautifulSoup


# 自己的id
my_id = 421
# 登陆用的cookie
after_cookie='PHPSESSID=7ujf30s2rm7jcgibrdst6mfld5; HicourseSESSIONID=lauvov6audmvvehg2e6aiism10; b816448737fd5b8ac832b94a869ec7dc_13405=51eeHWngLmc5V8MRA9n5biflClKHktdAk3v3urszLcSIwvZ2utcu; 07bf2ad6a07c3199a9e80f31335ef267=ecb6VG6BGObVcybFs5nMojeS2uTsvn4%2FOFeo0cibcf08GWQwJ27ReTBrkSpd7DBOJtkD4TtejITWUISuoQYdqrTYV40EjfwF5NrCaqVwSK9fg%2FG2O%2BHZ%2FtokzsZ4dlFz937SbeMI0h8kgMrfu7AoibDiiTsaiGNoaR%2F4%2FRBZkdL6%2B9hgW3epphBFRmK1JsQ7xYQHq4MLHdv8RPpsJmkA7XnwgV8X%2FDupysUjl%2FJkYtLUqjb5bZsk1V%2BSQggOiXkwtRER4Db8TKw; c3cf13b9a8b9ecef0a16f55f764695a9_13405=889455dRedY7TGCSwd47gMCnATmjx8ZU3Ejq3n1WMjAHw86EOuiZNve0j0GbXdVH'

cookies = {}
for line in after_cookie.split(';'):
    key, value = line.split('=', 1) # 1代表只分一次
    cookies[key] = value


if __name__ == '__main__':
    course_urls = []    
    for i in range(14231, 14263):   # 第一章
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


