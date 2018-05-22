import urllib.request
import urllib.parse
import json
import sys

content = sys.argv[1]
url = 'http://fanyi.youdao.com/translate?smartresult=dict&smartresult=rule'
# header = {'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 '
#                         'Safari/537.36'}
data_raw = {'i': content,
            'from': 'AUTO',
            'to': 'AUTO',
            'smartresult': 'dict',
            'client': 'fanyideskweb',
            'salt': '1509164915462',
            'sign': '9cb460f0a7522f368e9189bd0f6bc614',
            'doctype': 'json',
            'version': '2.1',
            'keyfrom': 'fanyi.web',
            'action': 'FY_BY_REALTIME',
            'typoResult': 'false'}
data = urllib.parse.urlencode(data_raw).encode('utf-8')

response = urllib.request.urlopen(url, data)
html = response.read().decode('utf-8')
result_json = json.loads(html)

result = result_json['translateResult'][0][0]['tgt']
print(result)

if 'smartResult' in result_json:
    smart_result = result_json['smartResult']['entries']
    for i in range(len(smart_result)):
        print(smart_result[i])
