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
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MegaSoldier;

/**
 * Created by Karla Rosas on 26/05/2017.
 */

public class HelpScreen extends BaseScreen {

    private TextButton back;
    private Stage stage;
    private Image logo;
    private Skin skin;


    public HelpScreen(final MegaSoldier game)
    {
        super(game);
        stage = new Stage(new FitViewport(640, 360));
        skin = new Skin(Gdx.files.internal(("skin/star-soldier-ui.json")));
        logo = new Image(game.getManager().get("ayuda.png", Texture.class));
        back = new TextButton("->", skin);

        back.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game));
            }
        });
        logo.scaleBy(-.4f);
        logo.setPosition(420 - logo.getWidth()/2, 280 - logo.getHeight()/2);
        back.setSize(100, 70);
        back.setPosition(520, 30);
        stage.addActor(logo);
        stage.addActor(back);

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

