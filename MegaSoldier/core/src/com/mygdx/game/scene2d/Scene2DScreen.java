package com.mygdx.game.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.BaseScreen;
import com.mygdx.game.MainGame;

/**
 * Created by Jonathan on 04/05/2017.
 */

public class Scene2DScreen extends BaseScreen {

    public Scene2DScreen(MainGame game)
    {
        super(game);
        texturaJugador = new Texture("mario.png");
        texturaCuadros = new Texture("cuadros.png");
        regionCuadros = new TextureRegion(texturaCuadros);
    }

    private Stage stage;
    private com.mygdx.game.scene2d.ActorJugador jugador;
    private com.mygdx.game.scene2d.ActorCuadros cuadros;
    private Texture texturaJugador, texturaCuadros;
    private TextureRegion regionCuadros;


    @Override
    public void show() {
        stage = new Stage();
        stage.setDebugAll(true);

        jugador = new com.mygdx.game.scene2d.ActorJugador(texturaJugador);
        cuadros = new com.mygdx.game.scene2d.ActorCuadros(regionCuadros);
        stage.addActor(jugador);
        stage.addActor(cuadros);

        jugador.setPosition(20, 50);
        cuadros.setPosition(300, 50);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();

        comprobarColisiones();

        stage.draw();
    }

    private void comprobarColisiones()
    {
        if(jugador.isAlive() && jugador.getX() + 110 > cuadros.getX())
        {
            System.out.print("Colision");
            jugador.setAlive(false);
        }
    }

    @Override
    public void dispose() {
        texturaJugador.dispose();
    }
}

