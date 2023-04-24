from socket import *

server_name = "localhost"
server_socket = 12345
client_socket = socket(AF_INET, SOCK_DGRAM)
message = input("Please input your message: \n")

client_socket.sendto(message.encode(), (server_name, server_socket))
response_message, server_address = client_socket.recvfrom(2048)
print("response: {}".format(response_message))
print("from: {}".format(server_address))

client_socket.close()