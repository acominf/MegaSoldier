### Objetivo del juego/aplicación
Desarrollar la logica en el jugador para que pueda resolver las situaciones que se presentan en el transcurso del juego.

### Descripción del juego/aplicación
Juego de plataforma clasico donde el jugador tendra que abrirse paso por un mundo que tiene ciertos obstaculos como enemigos que haran todo lo que puedan por detener a nuestro heroe a su vez tendra que buscar objetos que le ayudaran a completar su aventura.

### Clases principales y sus características
1. MegaSoldier
* Carga todas las imagenes
* Inicializa todos las pantallas
* Cambia la pantalla a menu para comenzar

2. PlayScreen
* Inicializa el mapa
* Inicializa la camara que seguira al jugador
* Crea las condiciones fisicas del mundo como la gravedad
* Crea las condiciones de teclas para mover al personaje
* Crea la tabla de score, vidas y tiempo

3. Player
* Crea estados del personaje
* Define que si el personaje sigue vivo o ha muerto
* Cambia el sprite de acuerdo al estado

4. SoldadoMalo
* Crea la animacion del enemigo
* Ejecuta la accion al eliminar al enemigo


5. WorldContactListener
* Contiene colisiones
* Dectecta cuando un objeto a chocado con otro 
* Ejecuta las acciones cuando se crea una colision de acuerdo al tipo de colision que es

### Diagrama de clases
![Diagrama de clases](https://github.com/acominf/MegaSoldier/blob/master/Imagenes/MegaSoldier.png)

### Autor(es)
Los autores del proyecto son:
- Karla Stephania Rosas Del Valle (k2rosvall)
- Jonathan Israel Baro del Leon (jonathan.baro21)

### Materia(s)
- Programación Orientada a Objetos

### Semestre
- 2016-2017/II

### Markdown
El contenido de esta página está escrito en un lenguaje de marcado sencillo llamado *Markdown*. Para más detalles consulta la página de [Markdown para GitHub](https://guides.github.com/features/mastering-markdown/).

### Temas de Jekyll
El estilo y presentación de esta página utiliza el tema de Jekyll seleccionado en la configuración del repositorio. El nombre de este tema está almacenado en el archivo de configuración `_config.yml`. Para más información acerca de los temas de Jekyll soportados por GitHub [haz click en este enlace](https://pages.github.com/themes/).
