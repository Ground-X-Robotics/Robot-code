#
# Requires keyboard module, 'pip install keyboard' in cmd
#
import socket
import keyboard
start = "e"
host = input("Enter server IPv4 address: ")
port = int(input("Enter port: "))
while start != "":
    start = input("Press enter when ready").lower()
print("W - Forward\nA - Left\nD - Right\nS - Reverse\nAny other key - Halt\nX - Close connection to server\nZ - Change port")
def sendData(data):
    #Gets IP and port of server
    #port = 25565
    #Connects socket
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((host, port))
    #Checks what key is pressed (I used if statements because
    #s.sendall must be a string beginning with b
    if data == "w":
        s.sendall(b'Forward')
    elif data == "a":
        s.sendall(b'Left')
    elif data == "d":
        s.sendall(b'Right')
    elif data == "s":
        s.sendall(b'Reverse')
    elif data == "x":
        s.sendall(b'close server')
    else:
        s.sendall(b'Halt')
    #Waits until the server receives the data
    #data = s.recv(1024)
    #print(str(data))
    #Closes that instance of connection
    #s.close()
    #print('Received', repr(data))


while True:
    #Tests for key presses, for some reason sendData(keyboard.is_pressed())
    #didn't work but meh this works
    #try:
    if keyboard.is_pressed("w"):
        print("W")
        sendData("w")
    elif keyboard.is_pressed("a"):
        sendData("a")
    elif keyboard.is_pressed("s"):
        sendData("s")
    elif keyboard.is_pressed("d"):
        sendData("d")
    elif keyboard.is_pressed("x"):
        sendData("x")
    elif keyboard.is_pressed("z"):
        port = int(input("Enter new port: "))
    else:
        sendData("null")
    #except:
        #break
