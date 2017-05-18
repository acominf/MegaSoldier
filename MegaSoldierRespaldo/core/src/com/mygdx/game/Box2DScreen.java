package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Jonathan on 08/05/2017.
 */

public class Box2DScreen extends BaseScreen {
    public Box2DScreen(MainGame game) {
        super(game);
    }

    private World world;

    private Box2DDebugRenderer renderer;

    private OrthographicCamera camera;

    private Body marioBody, sueloBody, pinchoBody;   // embutir en una entidad

    private Fixture marioFixture, sueloFixture, pinchoFixture;  //Hacer una estructura lista para guardar lo necesario

    private boolean debeSaltar, marioSaltando, marioVivo = true;

    @Override
    public void show() {
        world = new World(new Vector2(0, -30), true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(24.11f, 16); // Cambia tamaño que captura la camara
        camera.translate(8,6);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA(), fixtureB = contact.getFixtureB();

                if ((fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("floor"))
                        || (fixtureA.getUserData().equals("floor") && fixtureB.getUserData().equals("player"))) {
                    if (Gdx.input.isTouched()) {
                        debeSaltar = true;
                    }
                    marioSaltando = false;
                }

                if ((fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("spike"))
                        || (fixtureA.getUserData().equals("spike") && fixtureB.getUserData().equals("player"))) {
                    marioVivo = false;
                }
            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA(), fixtureB = contact.getFixtureB();
                if( fixtureA == marioFixture && fixtureB == sueloFixture)
                {

                    marioSaltando = true;
                }

                if(fixtureA == sueloFixture && fixtureB == marioFixture)
                {
                    marioSaltando = true;
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        BodyDef marioDef = createMarioBodyDef();
        marioBody = world.createBody(marioDef);
        sueloBody = world.createBody(createSueloBodyDef());
        pinchoBody = world.createBody(createPinchoBodyDef(15));



        PolygonShape marioShape = new PolygonShape();
        marioShape.setAsBox(0.5f, 0.5f);
        marioFixture = marioBody.createFixture(marioShape, 1);
        marioShape.dispose();

        PolygonShape sueloShape = new PolygonShape();
        sueloShape.setAsBox(500, 1);
        sueloFixture = sueloBody.createFixture(sueloShape, 1);
        sueloShape.dispose();

        pinchoFixture = createPinchoFixture(pinchoBody);

        marioFixture.setUserData("player");
        sueloFixture.setUserData("floor");
        pinchoFixture.setUserData("spike");

    }

    private BodyDef createPinchoBodyDef(float x)
    {
        BodyDef def = new BodyDef();
        def.position.set(x, 0.5f);     // Para cambiar la posicion

        return def;
    }

    private BodyDef createSueloBodyDef()
    {
        BodyDef def = new BodyDef();
        def.position.set(0, -1);     // Para cambiar la posicion

        return def;
    }

    private  BodyDef createMarioBodyDef()
    {
        BodyDef def = new BodyDef();
        def.position.set(0,0.5f);     // Para cambiar la posicion
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    private Fixture createPinchoFixture(Body pinchoBody)
    {
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f, -0.5f);
        vertices[1] = new Vector2(0.5f, -0.5f);
        vertices[2] = new Vector2(0, 0.5f);
        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        Fixture fix = pinchoBody.createFixture(shape, 1);
        shape.dispose();
        return fix;
    }

    @Override
    public void dispose() {
        sueloBody.destroyFixture(sueloFixture);
        marioBody.destroyFixture(marioFixture);
        pinchoBody.destroyFixture(pinchoFixture);
        world.destroyBody(marioBody);
        world.destroyBody(sueloBody);
        world.destroyBody(pinchoBody);
        world.dispose();
        renderer.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (debeSaltar)
        {
            debeSaltar = false;
            saltar();
        }

        if (Gdx.input.justTouched() && !marioSaltando)
        {
            debeSaltar = true;
        }

        if(marioVivo) {
            float velocidadY = marioBody.getLinearVelocity().y;
            marioBody.setLinearVelocity(8, velocidadY);
        }

        world.step(delta, 6, 2);

        camera.update();
        renderer.render(world, camera.combined);
    }

    private void saltar()
    {
        Vector2 position = marioBody.getPosition();
        marioBody.applyLinearImpulse(0, 15, position.x, position.y, true);
    }

}
