import pymysql.cursors


connection = pymysql.connect(host='localhost',
                             user='root',
                             password='5523',
                             db='demo',
                             charset='utf8mb4',
                             cursorclass=pymysql.cursors.DictCursor)

try:
    with connection.cursor() as cur:
        # Create a new record
        sql = "INSERT INTO `users` (`email`, `password`) VALUES (%s, %s)"
        cur.execute(sql, ('qwe@rty.com', 'qwerty'))

    connection.commit()

    with connection.cursor() as cur:
        # Read a single record
        sql = "SELECT `id`, `password` FROM `users` WHERE `email`=%s"
        cur.execute(sql, ('qwe@rty.com',))
        result = cur.fetchall()
        print(result)
finally:
    connection.close()
