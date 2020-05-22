import time


def read_file(file_path: str):
    f = None
    try:
        f = open(file_path, 'r', encoding="utf-8")
        print(f.read())
    except FileNotFoundError:
        print("File Not Found!")
    except LookupError:
        print("Unknown Encoding!")
    except UnicodeDecodeError:
        print("Decoding Error!")
    finally:
        if f:
            f.close()

    # 一次性读取整个文件内容
    print("一次性读取整个文件内容")
    with open(file_path, 'r', encoding='utf-8') as f:
        print(f.read())

    # 通过for-in循环逐行读取
    print("通过for-in循环逐行读取")
    with open(file_path, mode='r') as f:
        for line in f:
            print(line, end='')
            time.sleep(0.5)
    print()

    # 读取文件按行读取到列表中
    print("读取文件按行读取到列表中")
    with open(file_path) as f:
        lines = f.readlines()
    print(lines)


if __name__ == '__main__':
    read_file('/Users/hasaker/Documents/config/start_redis_cluster.sh')
