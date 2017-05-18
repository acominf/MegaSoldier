package com.mygdx.game.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Jonathan on 04/05/2017.
 */

public class ActorCuadros extends Actor{

    private TextureRegion cuadros;

    public ActorCuadros(TextureRegion cuadros)
    {
        this.cuadros = cuadros;
        setSize(cuadros.getRegionWidth(), cuadros.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        setX(getX() - 50 * delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(cuadros, getX(), getY(), 250, 150);
    }
}
