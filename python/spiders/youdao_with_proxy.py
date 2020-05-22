import urllib.request
import random

url = 'http://www.whatismyip.com'
iplist = ['120.27.49.85:8090', '120.27.100.78:80']
proxy_support = urllib.request.ProxyHandler({'http': random.choice(iplist)})
opener = urllib.request.build_opener(proxy_support)
opener.addheaders = [
    ('User-Agent', 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 '
                   'Safari/537.36')]
urllib.request.install_opener(opener)

respose = urllib.request.urlopen(url)
html = respose.read().decode('utf-8')

print(html)
