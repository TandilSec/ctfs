# tandilsec_ctf_writeup

CTF - Mr. Robot

Type:

Level: beginner-intermediate.

Previous knowledge:

Meta: Encontrar 3 claves escondidas en diferentes locaciones

Note:

# Walkthrough

Encontrar la IP de una maquina especifica dentro de una red (la IP comienza con 172.29.81...)

Hacemos REQUESTS con Zaproxy (OWASP)

Analizamos la direccion IP mediante NMap: Tiene activos los puertos 80 y 433, se trata de una Web. 

Al ingresar en el browser IP (http/172.29.81.94) nos devuelve flag1of3 (encontramos la 1er pista)

Ahora debemos saber si se trata de un CMS
Al agregar /wpadmin a la direccion descubrimos que se trata de un Wordpress

Mediante http/172.29.81.94robots.txt llegamos a fsocity un diccionario

Descargamos el diccionario
wget 172.29.81.94/fsocity.dicc

Removemos elementos duplicados
/Documentos grep fsocity.dic sort-u

Probamos como user de Wordpress cada campo del diccionario purgado (este paso conviene automatizarlo) : Si el user no existe 
Wordpress tira un error, si el user existe Wordpress tira otro tipo diferente de error. (Brute Force Attack)

Para los users existentes probamos todos los campos de ese diccionario hasta dar con la password.

# Herramientas utilizadas: nmap, zaproxy
