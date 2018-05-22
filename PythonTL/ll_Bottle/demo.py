# usr/bin/env python
# -*- coding: utf-8 -*-

from bottle import Bottle, run, template


app = Bottle()


@app.route('/hello/<name>')
def hello(name="Stranger"):
    return template('Hello {{name}}, How are you?', name=name)


run(host="localhost", port=5000, debug=True)
