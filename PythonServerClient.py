import socket

#host = input("Enter IP: ")
host = '192.168.0.17'
port = 11223
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((host, port))

print (host, port)
s.listen(1)
conn, addr = s.accept()
print('Connected by', addr)
while True:
    data = conn.recv(1024)
    while not data:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind((host, port))
        s.listen(1)
        conn, addr = s.accept()
        data = conn.recv(1024)
        print(str(data))
    print("Client says: "+str(data))
    conn.sendall(b"Received")
    if str(data) == "b'close server'": break
conn.close()
