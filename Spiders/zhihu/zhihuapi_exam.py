import zhihuapi as api


api.cookie('q_c1=ca8fd9b0058c466488055e89ba06cddf|1482502037000|1482502037000; l_cap_id="MjkwYTc4MjE4ZGI2NGYyNmIxNzA1MzQ0NGY3MGMxZjY=|1482502037|bc189cf3bc979ea0f01db5043ad02bfb93e1012f"; cap_id="YmI0ODQ5YzAxNjk0NGM2ODk4NzI1MDZlNTlmNzkyMGE=|1482502037|e2ce9bd946acd9e7a576faa448de72a0772f28bd"; d_c0="AIBC8CMUCwuPTkAJs9T5Of8PDF9gZ7ZLD9A=|1482502038"; _zap=84d26f58-60b6-4810-974d-2d58d422ecb6; r_cap_id="OTc1NmI0MmJhYzMwNGNkNTg5OGY0Mjk2ODM3YWE0NDc=|1482502038|eb2f1d18c108f58a60bb30f3fc939b12fc5aa3ca"; _xsrf=e929aec172d85d19fc16324bdae27fe5; __utmt=1; s-q=zhihuapi; s-i=1; sid=pvst9jmo; s-t=autocomplete; z_c0=Mi4wQUFDQUJNWWFBQUFBZ0VMd0l4UUxDeGNBQUFCaEFsVk5xTHlFV0FERW5BVFJYbTJ5WlpRLXptUVhpU1hjT0kwMlln|1482831707|62c765f1e2dcdf862e9b08dd400be67d3c5e77a1; __utma=51854390.285324067.1482831707.1482831707.1482831707.1; __utmb=51854390.2.10.1482831707; __utmc=51854390; __utmz=51854390.1482831707.1.1.utmcsr=zhuanlan.zhihu.com|utmccn=(referral)|utmcmd=referral|utmcct=/p/24573468; __utmv=51854390.100-1|2=registration_date=20130410=1^3=entry_date=20130410=1')
with open('config.ini') as f:
	api.cookie(f.read())

data = api.user('zhihuadmin').profile()
print(data)
