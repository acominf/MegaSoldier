package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MegaSoldier;

/**
 * Created by Karla Rosas on 26/05/2017.
 */

public abstract class BaseScreen implements Screen {

    public MegaSoldier game;

    public BaseScreen(MegaSoldier game)
    {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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

    }
}
