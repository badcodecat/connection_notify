#!/usr/bin/env python3

import plyer

import socket

ADDRESS = '192.168.200.2'
PORT = 14141

def main():
	client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	client.connect((ADDRESS, PORT))
	while True:
		data = client.recv(256)
		if not data:
			break
		data = data.decode('utf-8')
		print(data)
		plyer.notification.notify(title='ConnectionNotify', message=data, toast=True)
	client.shutdown(socket.SHUT_RDWR)
	client.close()


if __name__ == '__main__':
	main()
