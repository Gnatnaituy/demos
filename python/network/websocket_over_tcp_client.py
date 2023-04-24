from socket import *

client_socket = socket(AF_INET, SOCK_STREAM)
client_socket.connect(('127.0.0.1', 12312))
sentence = input('Please input somthing...\n')
client_socket.send(sentence.encode())
modified_sentence = client_socket.recv(1024)
print('Receive from server: ', modified_sentence)
client_socket.close()