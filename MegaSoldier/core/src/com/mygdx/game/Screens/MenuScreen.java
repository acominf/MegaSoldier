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

/**
 * Created by Karla Rosas on 26/05/2017.
 */

public class MenuScreen extends BaseScreen {


    private Stage stage;
    protected Skin skin;
    private Image logo;
    private TextButton play;
    private TextButton creditos;


    public MenuScreen(final MegaSoldier game) {
        super(game);
        stage = new Stage(new FitViewport(640, 360));
        skin = new Skin(Gdx.files.internal(("skin/star-soldier-ui.json")));
        logo = new Image(game.getManager().get("logo.png", Texture.class));
        play = new TextButton("Play", skin);
        creditos = new TextButton("Creditos", skin);

        play.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(game.playScreen);

            }
        });

        creditos.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.creditScreen);
            }
        });

        logo.setPosition(320 - logo.getWidth()/2, 250 - logo.getHeight()/2);
        play.setSize(200, 100);
        play.setPosition(100, 50);
        creditos.setSize(200, 100);
        creditos.setPosition(400, 50);

        stage.addActor(play);
        stage.addActor(logo);
        stage.addActor(creditos);

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


