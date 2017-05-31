### Objetivo del juego/aplicación
Desarrollar la logica en el jugador para que pueda resolver las situaciones que se presentan en el transcurso del juego.

### Descripción del juego/aplicación
Juego de plataforma clasico donde el jugador tendra que abrirse paso por un mundo que tiene ciertos obstaculos como enemigos que haran todo lo que puedan por detener a nuestro heroe a su vez tendra que buscar objetos que le ayudaran a completar su aventura.

Se cuenta con 5 vidas, el mapa cuenta con objetos que sumaran puntos y daran vidas si se golpean.
Tambien estan los enemigos repartidos por el mapa, si los tocas la vida disminuira.
Ganas al llegar al otro lado del mapa y tocar la tuberia.

### Clases principales y sus características
1. MegaSoldier
* Carga todas las imagenes
* Inicializa todos las pantallas
* Cambia la pantalla a menu para comenzar

2. PlayScreen
* Inicializa la camara
* Inicializa el mapa
* Crea al jugador
* Inicializa el tablero donde se ven las vidas, los puntos y el tiempo

3. WorldContactListener
* Dectecta las colisiones en el juego
* Activa las acciones cuando se dectecta una colison entre objetos

4. SoldadoMalo
* Crea al enemigo 
* Determina si el a sido destruido o no
* Reproduce el sonido cuando el enemigo a sido destruido

5. Player
* Crea al Player
* Determina si el a sido tocado o no
* Tiene los metodos para caminar y saltar
* Incrementa y decrementa vidas


### Diagrama de clases
![Diagrama de clases](https://raw.githubusercontent.com/acominf/MegaSoldier/master/Imagenes/MegaSoldier.png)

### Logo 
![Logo](https://raw.githubusercontent.com/acominf/MegaSoldier/master/Imagenes/logo.png)

Imagen principal del videojuego

### Personaje
![Personaje](https://raw.githubusercontent.com/acominf/MegaSoldier/master/Imagenes/player.png)

Sprites del personaje, para simular que el personaje camina, las imagenes se diseñaron en el programa de Piskel.

### Enemigo
![Enemigo](https://raw.githubusercontent.com/acominf/MegaSoldier/master/Imagenes/enemies.png)

Sprites de los enemigos, para simula cuando los enemigos caminan

### Tuberia
![Tuberia](https://raw.githubusercontent.com/acominf/MegaSoldier/master/Imagenes/tuberia.png)

Puerta que se debe alcanzar para poder ganar el juego

### Objeto
![Objeto](https://raw.githubusercontent.com/acominf/MegaSoldier/master/Imagenes/objeto.png)

Al golpear este objeto se obtienen vidas extras asi como puntos.

### Brick
![Brick](https://raw.githubusercontent.com/acominf/MegaSoldier/master/Imagenes/brick.png)

Muro que se puede destruir si se golpea con la cabeza.


### ScreenDelJuego
![MegaSoldier1](https://raw.githubusercontent.com/acominf/MegaSoldier/master/Imagenes/MegaSoldier1.png)

Menu principal del juego, muestra el logo y los botones de creditos y jugar, que te mandan a las siguientes pantallas. 
![MegaSoldier6](https://raw.githubusercontent.com/acominf/MegaSoldier/master/Imagenes/MegaSoldier6.png)

Pantalla de creditos donde se muestran el nombre de los desarroladores
![MegaSoldier2](https://raw.githubusercontent.com/acominf/MegaSoldier/master/Imagenes/MegaSoldier2.png)

Al presionar play se manda la pantalla de ayuda donde te muestra los controles del juego, tambien tiene un boton de siguiente.
![MegaSoldier3](https://raw.githubusercontent.com/acominf/MegaSoldier/master/Imagenes/MegaSoldier3.png)
![MegaSoldier4](https://raw.githubusercontent.com/acominf/MegaSoldier/master/Imagenes/MegaSoldier4.png)
![MegaSoldier5](https://raw.githubusercontent.com/acominf/MegaSoldier/master/Imagenes/MegaSoldier5.png)

Cuando se presiona siguiente en la pantalla de ayuda se inicia el juego, donde se tiene que ir avanzado y se encuentran enemigos y objetos con los cuales se puede interactuar, el mapa se creo en el programa Tiled.
![MegaSoldier6](https://raw.githubusercontent.com/acominf/MegaSoldier/master/Imagenes/MegaSoldier7.png)

Pantalla de Game over que se muestra si el jugador pierde todas sus vidas, tiene los botones de retry para comenzar de nuevo y el boton menu, para regresar al menu
![MegaSoldier6](https://raw.githubusercontent.com/acominf/MegaSoldier/master/Imagenes/MegaSoldier8.png)

Pantalla que se muestra al completar el juego, este pantalla solo cuenta con un boton para regresar al menu principal.



### Autor(es)
Los autores del proyecto son:
- Karla Stephania Rosas Del Valle (k2rosvall)
- Jonathan Israel Baro de Leon (jonathan.baro21)

### Materia(s)
- Programación Orientada a Objetos

### Semestre
- 2016-2017/II

## Video Demostrativo
<iframe width="560" height="315" src="https://www.youtube.com/embed/mFur5aXU9mo" frameborder="0" allowfullscreen></iframe>

### Markdown
El contenido de esta página está escrito en un lenguaje de marcado sencillo llamado *Markdown*. Para más detalles consulta la página de [Markdown para GitHub](https://guides.github.com/features/mastering-markdown/).

### Temas de Jekyll
El estilo y presentación de esta página utiliza el tema de Jekyll seleccionado en la configuración del repositorio. El nombre de este tema está almacenado en el archivo de configuración `_config.yml`. Para más información acerca de los temas de Jekyll soportados por GitHub [haz click en este enlace](https://pages.github.com/themes/).
