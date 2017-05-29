package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MegaSoldier;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.actor;

/**
 * Created by Karla Rosas on 28/05/2017.
 */

public class GameOverScreen extends BaseScreen {

    private TextButton retry;
    private TextButton menu;
    private Stage stage;
    private Image logo;
    private Skin skin;

    public GameOverScreen(final MegaSoldier game) {
        super(game);
        stage = new Stage(new FitViewport(640, 360));
        skin = new Skin(Gdx.files.internal(("skin/star-soldier-ui.json")));
        logo = new Image(game.getManager().get("gameover.png",Texture.class));
        retry = new TextButton("Retry", skin);
        menu = new TextButton("Menu", skin);


        retry.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(game.playScreen);
            }
        });
        menu.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.menuScreen);
            }
        });
        logo.scaleBy(-.4f);
        logo.setPosition(420 - logo.getWidth()/2, 310 - logo.getHeight()/2);
        retry.setSize(200, 100);
        retry.setPosition(100, 50);
        menu.setSize(200, 100);
        menu.setPosition(400, 50);
        stage.addActor(logo);
        stage.addActor(retry);
        stage.addActor(menu);
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide()
    {
        Gdx.input.setInputProcessor(null);

    }
    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }


}
