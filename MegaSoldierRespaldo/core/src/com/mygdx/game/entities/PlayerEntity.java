package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.mygdx.game.Constants;

import static com.mygdx.game.Constants.PIXEL_IN_METER;

/**
 * Created by Jonathan on 11/05/2017.
 */

public class PlayerEntity extends Actor {

    private Texture texture;

    private World world;

    private Body body, leftBody;

    private Fixture fixture, leftFixture;

    private boolean alive = true;
    private boolean jumping = false;

    public boolean isMustJump() {
        return mustJump;
    }

    public void setMustJump(boolean mustJump) {
        this.mustJump = mustJump;
    }

    private boolean mustJump = false;

    public PlayerEntity(World world, Texture texture, Vector2 position)
    {
        this.world = world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(position);     // Para cambiar la posicion
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        box.setAsBox(0.5f, 0.5f);
        fixture = body.createFixture(box, 1);
        fixture.setUserData("player");
        box.dispose();

        setSize(PIXEL_IN_METER, PIXEL_IN_METER);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
         setPosition((body.getPosition().x - 0.5f) * PIXEL_IN_METER,
                     (body.getPosition().y - 0.5f) * PIXEL_IN_METER);
         batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        //Iniciar salto si hemos tocado la pantalla
        if(Gdx.input.justTouched() || mustJump)
        {
            mustJump = false;
            jump();
        }
        //Hacer avanzar el jugador si esta vivo
        if (alive)
        {
            float speedY = body.getLinearVelocity().y;
            body.setLinearVelocity(Constants.PLAYER_SPEED, speedY);
        }
        if (jumping)
        {
            body.applyForceToCenter(0, -Constants.IMPULSE_JUMP * 1.05f, true);
        }
    }

    private void jump() {
        if (!jumping && alive) {
            jumping = true;
            Vector2 position = body.getPosition();
            body.applyLinearImpulse(0, Constants.IMPULSE_JUMP, position.x, position.y, true);
        }
    }

    public void detach()
    {
        body.destroyFixture(fixture);
        world.destroyBody(body);

    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}
