import requests
import re
import asyncio
from bs4 import BeautifulSoup


my_cookie = 'PHPSESSID=jthvu5gjf9eeh9ev0qn18ofkv5; HicourseSESSIONID=16t8n1nqtv93s2bsihvllum7t6; 07bf2ad6a07c3199a9e80f31335ef267=d337T3EMwwDEuct%2B5B6f5uy%2BuYtkSIKBITjRuxbDebr8HqnRGNl0RK13kTdnGUbNCptFJtuySPM5OuRN3bLPG3nulkhR0xa5re7lNb4gRPtrY1lktLCi5HyHc8Ja4hytGLaHRHZa6HREH9syfZduSRRUnKCFLQ%2F2k8bZZeYeFly8unRi9UwLbfPhiqlfTn2Ba2iFAyDqK0CIWNFpHzDOkl6fEWAxwIVXTnbhJ3PEGwWbo%2FBk%2BBxga97QvYvgmV9eIrXt93Ayjgg; b816448737fd5b8ac832b94a869ec7dc_13400=27dcroBcDXX0udUxFAAhnbaGX9OXFau3UR7cu1lyvM%2BydiwC%2BY6z'

cookies = {}
for line in my_cookie.split(';'):
    key, value = line.split('=', 1)
    cookies[key] = value


async def get_tag(course_urls):
    for course_url in course_urls:
        await click_url(course_url)


async def click_url(course_url):
    r = requests.get(course_url, cookies=cookies)
    soup = BeautifulSoup(r.content, 'html.parser')
    for tag in soup.find_all(href=re.compile("/portal/coursecontent/index/treeid/\d{5}/lessonid/\d{5}/id/\d{3}.html")):
        if 'xueguo_col' in str(tag):
            print('>>>>>> Already studied')
        else:
            await click_tag(tag)


async def click_tag(tag):
    requests.get('http://wxit.org/portal/coursecontent/index/treeid/' + str(i) +
                 '/lessonid/' + str(tag)[83:88] + '/id/421.html', cookies=cookies)
    print(str(tag)[83:88] + '>>>>>>Studing now')


if __name__ == '__main__':

    course_urls = []
    for i in range(14252, 14392): # 240 392
        course_urls.append(
            'http://wxit.org/portal/coursecontent/index/treeid/' + str(i) + '/id/421.html')

    loop = asyncio.get_event_loop()
    loop.run_until_complete(get_tag(course_urls))
    loop.close()
