package com.mygdx.game.scene2d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Jonathan on 04/05/2017.
 */

public class ActorJugador extends Actor {

    private Texture jugador;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    private  boolean alive;

    public ActorJugador(Texture jugador)
    {

        this.jugador = jugador;
        this.alive = true;
        setSize(jugador.getWidth(), jugador.getHeight());
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(jugador, getX(), getY(), 100, 100);
    }
}
