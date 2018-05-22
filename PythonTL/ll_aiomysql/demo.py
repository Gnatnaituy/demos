import asyncio
import aiomysql


loop = asyncio.get_event_loop()


@asyncio.coroutine
def test_example():

    # Connection
    connection = yield from aiomysql.connect(host='127.0.0.1', port=3306,
                                             user='root', password='5523',
                                             db='mysql', loop=loop)
    # Create default cursor
    cur = yield from connection.cursor()

    # Execute sql query
    yield from cur.execute("SELECT Host, User FROM user")

    # Fetch all results
    # r = yield from cur.fetchall()
    # print('fetchall():' + str(r))

    # Fetch many results
    limitr = yield from cur.fetchmany(2)
    print('fetchmany():' + str(limitr))

    # Detach cursor from connection
    yield from cur.close()
    # Close connection
    connection.close()


loop.run_until_complete(test_example())

