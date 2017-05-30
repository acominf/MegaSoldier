package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MegaSoldier;
import com.mygdx.game.Sprites.Enemy;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.Sprites.SoldadoMalo;

/**
 * Created by Karla Rosas on 17/05/2017.
 */

public class Hud implements Disposable{

    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private static Integer score;
    private static Integer vidas;

    private Label countdownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private static Label levelLabel;
    private Label worldLabel;
    private Label MsLabel;
    private Label LiveLabel;

    public Hud(SpriteBatch sb)
    {
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        vidas = 5;

        viewport = new FitViewport(MegaSoldier.V_WIDTH, MegaSoldier.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top(); //Para dibujar en la parte de arriba de la pantalla
        table.setFillParent(true); //Para hacer el table de tamaño del stage

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.RED));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.RED));
        timeLabel=  new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.RED));
        levelLabel = new Label(String.format("%01d", vidas), new Label.LabelStyle(new BitmapFont(), Color.RED));
        LiveLabel = new Label("LIVES", new Label.LabelStyle(new BitmapFont(), Color.RED));
        MsLabel = new Label("MEGA SOLDIER", new Label.LabelStyle(new BitmapFont(), Color.RED));


        table.add(MsLabel).expandX().padTop(10);
        table.add(LiveLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);
    }


    public void update(float dt)
    {
        timeCount += dt;
        if(timeCount >= 1)
        {
            worldTimer--;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }


    public static void addScore(int value)
    {
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    public static void addVidas(int value)
    {
        vidas = value;
        levelLabel.setText(String.format("%01d", vidas));
    }

    public static void subVidas(int value)
    {
        System.out.print(value);
        vidas = value -1 ;
        levelLabel.setText(String.format("%01d",vidas));
    }



    @Override
    public void dispose() {
        stage.dispose();
    }
}
