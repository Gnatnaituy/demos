from socket import *

server_port = 12312
server_socket = socket(AF_INET, SOCK_STREAM)
server_socket.bind(('', server_port))
server_socket.listen(1)
print("TCP Server ready to receive message...")

while True:
    connection_socket, client_address = server_socket.accept()
    message = connection_socket.recv(1024)
    decode_message = message.decode()
    print("message: {}".format(decode_message))
    print("from: {} - {}".format(client_address, connection_socket))

    upper_message = decode_message.upper()
    connection_socket.send(upper_message.encode())