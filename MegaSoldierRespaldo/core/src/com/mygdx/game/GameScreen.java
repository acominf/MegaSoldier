package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.entities.EnemyEntity;
import com.mygdx.game.entities.PlayerEntity;
import com.mygdx.game.entities.FloorEntity;
import com.mygdx.game.entities.SpikeEntity;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jonathan on 11/05/2017.
 */

public class GameScreen extends BaseScreen {

    private Stage stage;
    private World world;
    private PlayerEntity player;

    private List<FloorEntity> floorList = new ArrayList<FloorEntity>();
    private List<SpikeEntity> spikeList = new ArrayList<SpikeEntity>();
    private List<EnemyEntity> enemyList = new ArrayList<EnemyEntity>();


    public GameScreen(MainGame game) {
        super(game);
        stage = new Stage(new FitViewport(640, 360));
        world = new World(new Vector2(0, -10), true);

        world.setContactListener(new ContactListener() {

            private boolean areCollided(Contact contact, Object userA, Object userB){
                return (contact.getFixtureA().getUserData().equals(userA) && contact.getFixtureB().getUserData().equals(userB)) ||
                        (contact.getFixtureA().getUserData().equals(userB) && contact.getFixtureB().getUserData().equals(userA));
            }

            @Override
            public void beginContact(Contact contact) {
                if (areCollided(contact, "player", "floor"))
                {
                    player.setJumping(false);
                    if (Gdx.input.isTouched())
                    {
                        player.setMustJump(true);
                    }
                }
                if (areCollided(contact, "player", "cuadros"))
                {
                    player.setAlive(false);
                    System.out.print("GAMEOVER");
                }
                if (areCollided(contact, "cuadros", "floor"))
                {
                   // System.out.print("SALE");
                }

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });


    }

    @Override
    public void show() {
        Texture playerTexture = game.getManager().get("guerrero.png");
        Texture floorTexture = game.getManager().get("floor.png");
        Texture overfloorTexture = game.getManager().get("overfloor.png");
        Texture spikeTexture = game.getManager().get("cuadros.png");
        player = new PlayerEntity(world, playerTexture, new Vector2(1.5f, 1.5f));

        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture,0, 1000, 1));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture,12, 10, 2));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture,40, 10, 3));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture,75, 15, 2));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture,80, 5, 4));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture,85, 3, 6));
        enemyList.add(new EnemyEntity(world, spikeTexture, new Vector2(10, 1)));
        enemyList.add(new EnemyEntity(world, spikeTexture, new Vector2(20, 2)));
        enemyList.add(new EnemyEntity(world, spikeTexture, new Vector2(30, 1)));
        enemyList.add(new EnemyEntity(world, spikeTexture, new Vector2(50, 1)));
        enemyList.add(new EnemyEntity(world, spikeTexture, new Vector2(70, 1)));


        stage.addActor(player);

        for (FloorEntity floor : floorList)
            stage.addActor(floor);
        for (SpikeEntity spike : spikeList)
            stage.addActor(spike);
        for (EnemyEntity enemy : enemyList)
            stage.addActor(enemy);



    }

    @Override
    public void hide() {
        player.detach();
        player.remove();
        for (FloorEntity floor : floorList)
            floor.detach();
        for (SpikeEntity spike : spikeList)
            spike.detach();
        for (EnemyEntity enemy : enemyList)
            stage.addActor(enemy);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(player.getX() > 150 && player.isAlive())
        {
            stage.getCamera().translate(Constants.PLAYER_SPEED * delta * Constants.PIXEL_IN_METER, 0, 0);
        }


        stage.act();
        world.step(delta, 6, 2);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }
}
