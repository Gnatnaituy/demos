import asyncio
import aiomysql


loop = asyncio.get_event_loop()


@asyncio.coroutine
def test_example():

    connection = yield from aiomysql.connect(host='127.0.0.1', port=3306,
                                             user='root', password='5523',
                                             db='mysql', loop=loop)
    cur = yield from connection.cursor()
    yield from cur.execute("SELECT Host, User FROM user")
    print(cur.description)
    r = yield from cur.fetchall()
    print(r)
    yield from cur.close()
    connection.close()


loop.run_until_complete(test_example())

