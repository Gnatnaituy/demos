import urllib.request
import os


def url_open(url):
    req = urllib.request.Request(url)
    req.add_header('User-Agent',
                   'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 '
                   'Safari/537.36')
    response = urllib.request.urlopen(req)
    html = response.read()  # 不decode因为图片为binary不能为utf-8

    return html


def get_page_number(url):
    html = url_open(url).decode('utf-8')

    a = html.find('current-comment-page') + 23
    b = html.find(']', a)   # a为起始位置

    return html[a:b]


def find_img_addrs(url):
    html = url_open(url).decode('utf-8')
    img_addrs = []

    a = html.find('img src=')
    while a != -1:
        b = html.find('.jpg', a, a+255)     # a+255表示一个范围(一个网址范围不会超过255)
        if b != -1:
            img_addrs.append('http://' + html[a+11:b+4])
        else:
            b = a + 11      # 跳过img src="//
        a = html.find('img src=', b)

    return img_addrs


def save_imgs(img_addrs):
    for i in img_addrs:
        filename = i.split('/')[-1]
        with open(filename, 'wb') as f:
            img = url_open(i)
            f.write(img)


def downloads_meizitu(pages=2):
    folder = '/home/hasaker/Documents/Spiders/Jiandan_meizitu'
    os.chdir(folder)

    url = "http://jiandan.net/ooxx/"
    page_num = int(get_page_number(url))

    for i in range(pages):
        page_num -= i
        page_url = url + 'page-' + str(page_num) + '#commets'
        img_addrs = find_img_addrs(page_url)
        save_imgs(img_addrs)


if __name__ == '__main__':
    downloads_meizitu()
