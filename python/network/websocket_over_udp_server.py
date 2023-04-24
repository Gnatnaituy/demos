from socket import *

server_port = 12345
server_socket = socket(AF_INET, SOCK_DGRAM)
server_socket.bind(('', server_port))
print("UDP Server ready to receive message...")

while True:
    message, client_address = server_socket.recvfrom(2048)
    
    decode_message = message.decode()
    print("message: {}".format(decode_message))
    print("from: {}".format(client_address))

    upper_message = decode_message.upper()
    server_socket.sendto(upper_message.encode(), client_address)