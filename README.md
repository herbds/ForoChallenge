![git7](https://github.com/user-attachments/assets/38f655c6-d838-47dd-94d5-d485a51bd0b1)
*Foro Challenge*
- Este proyecto puede necesitar inyeccion manual de un usuario y una contraseña a la base de datos, la contraseña necesaria es :"$2a$10$N3H2x4LL4N8cV8w.lJTORuzbVbVlnLz.0jqXQ8pKSRQUq4njNwqd6".
- http://localhost:8080/swagger-ui/index.html#/
-En el proyecto existe peticiones httpRequest:

1. Ingreso Usuario (Post) : http://localhost:8080/login
   {
	"login" : "Lucas",
	"clave" : "123456"
  }

2. Ingreso Problematica (Post) : http://localhost:8080/topicos
   {
		"nombreCurso": "Alura foro final",
    "titulo": "Alura challenge foro final",
		"mensaje": "Alura",
    "profesor": "Nombre profesor" 
  }

3. Actualizar Problematicas (Put) : http://localhost:8080/topicos
    {
	"topico" : "14",
	"estado" : "Investigado",
	"mensaje" : "Servicio Investigado"
  }

4. Eliminar Problematica (Del) : http://localhost:8080/topicos
   {
	"topico" : "14"
  }

5. Problematicas Actuales (Get) : http://localhost:8080/topicos
6. Consulta Problematicas (Get) : http://localhost:8080/topicos/14


![git1](https://github.com/user-attachments/assets/4c06e347-de4d-4c44-b916-9849ac8a2abf)
![git2](https://github.com/user-attachments/assets/ef393bcb-dbf8-4e39-b6ca-11e0dcf43b57)
![git3](https://github.com/user-attachments/assets/bc032c36-4c69-47a2-b52c-04953585f2e1)
![git4](https://github.com/user-attachments/assets/9740929a-240d-4d7a-a588-bbeb1024613a)
![git5](https://github.com/user-attachments/assets/f867b1bd-23b6-4ce3-8449-50f1b41808e4)
![git6](https://github.com/user-attachments/assets/c8ddb506-a8ce-423a-b7b1-8b4507672fb3)
