import requests
import re
import asyncio
from bs4 import BeautifulSoup


my_cookie = 'b816448737fd5b8ac832b94a869ec7dc_13408=5aa4atlDoe5qyZ1MDsTol6aIffTrI%2F95Se0fn%2B0uXcFefaGQfDlC; ' \
            'b816448737fd5b8ac832b94a869ec7dc_13407=b9c0m64QZvpeIv9wbdo%2FGVKL6LghFLKs%2FnHYbBbQP7U4CvLFwxjC; ' \
            'PHPSESSID=nlnj7emm7o48f4kfilej7agfa3; HicourseSESSIONID=evfu1j0oehft48cf98mdd293v2; ' \
            '07bf2ad6a07c3199a9e80f31335ef267=97e4JvJGPlHuQjXQFiewadlFhU1PRKkbUv6VHQ79PhEuw9hxtoWNAQFEdDFxPmm4fuk' \
            '%2Fbm9loeS9PS2V3WMa1vLGlleAaF1NbJfQ2kpQSaAmIRuI4M6YtN4MXRW2s7BAMwU42JKNnsEZYl2oUDUtu5' \
            '%2FaeOccENgAnXa2J6wssuhvZirTt%2FlaiKyDhVDbzjaiI0z%2FZIvYfB1FZavNWxQQl07F2ND5SZThgNAT' \
            '%2FpKNHwMbIDeHwm5PnJs31aihprZtf%2FKC2c1sBsE; ' \
            'b816448737fd5b8ac832b94a869ec7dc_13411=5f41LJ%2FK3GE96aet5OFzM8%2FwrLFXe%2FqkTQ7PdYxE%2BRN6B9oDmpCT '

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
    for i in range(14252, 14392):  # 240 392
        course_urls.append(
            'http://wxit.org/portal/coursecontent/index/treeid/' + str(i) + '/id/421.html')

    loop = asyncio.get_event_loop()
    loop.run_until_complete(get_tag(course_urls))
    loop.close()
