handler = open('SalidaEsperadaBusquedas.txt')
x=""
for linea in handler:
	x=x+linea
handler.close()
handler2 = open('prubaconComentarios.txt')
y=""
for linea2 in handler2:
	y=y+linea2
	
handler2.close()
if(x==y):
	print("si")
else:
	print("no")
