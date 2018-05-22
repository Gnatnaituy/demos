import requests
import configparser


def create_session():
    cf = configparser.ConfigParser()
    cf.read('config.ini')
    cookies = cf.items('cookie')
    cookies = dict(cookie)
    from pprint import pprint
    pprint(cookie)
    email = cf.get('info', 'email')
    password = cf.get('info', 'password')

    session = requests.session()
    login_data = {'email': email, 'password': password}
    header = {
        'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36',
        'Host': 'www.zhihu.com',
        'Referer': 'http://www.zhihu.com/'
    }
    r = session.post('http://www.zhihu.com/login/email',
                     data=login_data, headers=header)
    if r.json()['r'] == 1:
        print('Login Failed, reason is: '),
        for m in r.json()['data']:
            print(r.json()['data'][m])
        print('So we use cookies to login in ...')
        has_cookies = False
        for key in cookies:
            if key != '__name__' and cookies[key] != '':
                has_cookies = True
                break
        if has_cookies is False:
            raise ValueError('Please input config.ini.')
        else:
            r = session.get('http://www.zhihu.com/login/email',
                            cookies=cookies)

    with open('login.html', 'w') as fp:
        fp.write(r.content)

    return session, cookies


if __name__ == '__main__':
    requests_session, requests_cookies = create_session()

    url = 'http://www.zhihu.com/topic/19552832'
    content = requests_session.get(url, cookies=requests_cookies).content
    with open('url.html', 'w') as fp:
        fp.write(content)
