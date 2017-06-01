package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MegaSoldier;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.Enemy;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.Tools.B2WorldCreator;
import com.mygdx.game.Tools.WorldContactListener;

/**
 * Created by Karla Rosas on 17/05/2017.
 */

public class PlayScreen implements Screen{

    private MegaSoldier game;
    private TextureAtlas atlas;


    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    //Variables para el mapa
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    //Variables de Box2D
    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;
    //Juagador
    private Player player;

    private Music music;


    public PlayScreen(MegaSoldier game)
    {
        atlas = new TextureAtlas("Soldado.pack");

        this.game = game;
        //Crear camara para seguir al personaje en el mundo
        gamecam = new OrthographicCamera();
        //Crear un FitViewport para mantener el tamaño del juego
        gamePort = new FitViewport(MegaSoldier.V_WIDTH /MegaSoldier.PPM, MegaSoldier.V_HEIGHT / MegaSoldier.PPM, gamecam);
        //Crea el HUD para visualizar el score, el tiempo y la informacion del nivel
        hud = new Hud(game.batch);
        //Renderizar del mapa
        maploader = new TmxMapLoader();
        map = maploader.load("World.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / MegaSoldier.PPM);
        //Centrar la camara
        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        creator = new B2WorldCreator(this);

        //Inicializa Jugador
        player = new Player(this);

        world.setContactListener(new WorldContactListener());

        music = MegaSoldier.manager.get("Audio/Music/soundsGame.wav", Music.class);
        music.setLooping(true);
        music.play();


    }

    public TextureAtlas getAtlas()
    {
        return atlas;
    }

    public void handleInput(float dt)
    {
        if (player.getCurrentState() != Player.State.DEAD) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                player.getB2body().applyLinearImpulse(new Vector2(0, 3f), player.getB2body().getWorldCenter(), true);
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
                player.getB2body().applyLinearImpulse(new Vector2(0, -3f), player.getB2body().getWorldCenter(), true);
            if (Gdx.input.isKeyPressed((Input.Keys.RIGHT)) && player.getB2body().getLinearVelocity().x <= 2)
                player.getB2body().applyLinearImpulse(new Vector2(0.2f, 0), player.getB2body().getWorldCenter(), true);
            if (Gdx.input.isKeyPressed((Input.Keys.LEFT)) && player.getB2body().getLinearVelocity().x >= -2)
                player.getB2body().applyLinearImpulse(new Vector2(-0.2f, 0), player.getB2body().getWorldCenter(), true);
        }
    }
    //Actualizar si alguna tecla esta siendo presionada
    public void update(float dt)
    {
        handleInput(dt);
        world.step(1/60f, 6, 2);
        player.update(dt);
        for (Enemy enemy : creator.getEnemigos())
            enemy.update(dt);
        hud.update(dt);

        if (player.getCurrentState() != Player.State.DEAD)
        {
            gamecam.position.x = (player.getB2body().getPosition().x) + 1;
            gamecam.position.y = (player.getB2body().getPosition().y) + 0.7f;
        }

        gamecam.update();
        //Solo renderiza lo que la camara alcanza a ver
        renderer.setView(gamecam);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1); //Color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Limpia la pantalla

        renderer.render();
        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        for (Enemy enemy : creator.getEnemigos())
            enemy.draw(game.batch);
        game.batch.end();

        //Set our batch to now draw what the Hud camera sees.
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if (player.getVidas() == 0)
        {
            game.setScreen(new GameOverScreen(game));
            dispose();
        }
        if (player.getLevel() == 1)
        {
            game.setScreen(new WinScreen(game));
            dispose();
        }

    }

    public boolean gameOver(){
        if(player.getCurrentState() == Player.State.DEAD && player.getStateTimer() > 3)
        {
            return true;
        }
        return false;
    }

    @Override
    public void resize(int width, int height) {

        gamePort.update(width, height);

    }

    public TiledMap getMap()
    {
        return map;
    }

    public World getWorld()
    {
        return world;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();

    }

    public Hud getHud()
    {
        return hud;
    }
}
