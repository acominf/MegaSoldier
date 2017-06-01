package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MegaSoldier;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by Karla Rosas on 22/05/2017.
 */

public class Player extends Sprite {


    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State getPreviousState() {
        return previousState;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Body getB2body() {
        return b2body;
    }

    public void setB2body(Body b2body) {
        this.b2body = b2body;
    }

    public enum State{ FALLING, JUMPING, STANNDING, RUNNING, DEAD}

    private State currentState;
    private State previousState;
    private World world;
    private Body b2body;
    private TextureRegion playerStand;
    private Animation playerRun;
    private Animation playerJump;
    private TextureRegion playerDead;
    private float stateTimer;
    private boolean runnningRight;
    private boolean playerIsDead;
    private PlayScreen screen;
    private static int vidas;
    private static int level;

    public Player(PlayScreen screen)
    {
        super(screen.getAtlas().findRegion("player"));
        this.setWorld(screen.getWorld());
        setCurrentState(State.STANNDING);
        setPreviousState(State.STANNDING);
        stateTimer = 0;
        runnningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 4; i++)
            frames.add(new TextureRegion(getTexture(), i * 192, 0, 192, 164 ));
        playerRun = new Animation(0.1f, frames);
        frames.clear();
        for(int i = 1; i < 2; i++)
            frames.add(new TextureRegion(getTexture(), i * 1, 0, 192, 164 ));
        playerJump = new Animation(0.1f, frames);
        frames.clear();


        playerStand = new TextureRegion(getTexture(), 580, 0, 192, 164);

        playerDead = new TextureRegion(getTexture(), 580, 0, 192, 164);
        definePlayer();
        setBounds(0, 0, 33 / MegaSoldier.PPM, 33 / MegaSoldier.PPM);
        setRegion(playerStand);
        vidas = 5;
        level = 0;
    }

    public void update(float dt)
    {
        setPosition(getB2body().getPosition().x - getWidth() / 2, getB2body().getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt)
    {
        setCurrentState(getState());

        TextureRegion region;
        switch (getCurrentState()){
            case DEAD:
                region = playerDead;
                break;
            case JUMPING:
                region = (TextureRegion) playerJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = (TextureRegion) playerRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANNDING:
            default:
                region = playerStand;
                break;
        }

        if((getB2body().getLinearVelocity().x < 0 || !runnningRight) && !region.isFlipX())
        {
            region.flip(true, false);
            runnningRight = false;
        }
        else if((getB2body().getLinearVelocity().x > 0 || runnningRight) && region.isFlipX())
        {
            region.flip(true, false);
            runnningRight = true;
        }

        stateTimer = getCurrentState() == getPreviousState() ? stateTimer + dt : 0;
        setPreviousState(getCurrentState());
        return region;
    }

    public State getState()
    {
        if(playerIsDead)
            return State.DEAD;
        else if(getB2body().getLinearVelocity().y > 0 || (getB2body().getLinearVelocity().y < 0 && getPreviousState() == State.JUMPING))
            return State.JUMPING;
        else if(getB2body().getLinearVelocity().y < 0)
            return State.FALLING;
        else if(getB2body().getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANNDING;
    }

    public void definePlayer()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(35 / MegaSoldier.PPM,40 /MegaSoldier.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        setB2body(getWorld().createBody(bdef));

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15/MegaSoldier.PPM);
        fdef.filter.categoryBits = MegaSoldier.PLAYER_BIT;
        fdef.filter.maskBits = MegaSoldier.GROUND_BIT |
                               MegaSoldier.OBJETO_BIT |
                               MegaSoldier.BRICK_BIT |
                               MegaSoldier.ENEMY_BIT |
                               MegaSoldier.OBJECT_BIT |
                               MegaSoldier.ENEMY_HEAD_BIT;

        fdef.shape = shape;
        getB2body().createFixture((fdef));

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / MegaSoldier.PPM, 16 / MegaSoldier.PPM), new Vector2(2 / MegaSoldier.PPM, 16 / MegaSoldier.PPM));
        fdef.shape = head;
        fdef.isSensor = true;

        getB2body().createFixture(fdef).setUserData("head");

    }

    public void hit()
    {
        die();
    }

    public void die() {

        if (!isDead()) {
            playerIsDead = true;
            Filter filter = new Filter();
            filter.maskBits = MegaSoldier.NOTHING_BIT;


            for (Fixture fixture : getB2body().getFixtureList()) {
                fixture.setFilterData(filter);
            }

            getB2body().applyLinearImpulse(new Vector2(0, 4f), getB2body().getWorldCenter(), true);
        }
    }

    public static void disminuyeVida()
    {
        Hud.subVidas(vidas--);
    }

    public static void aumentaVida()
    {
        Hud.addVidas(vidas+=1);
    }

    public static void addLevel()
    {
        level = 1;
    }

    public boolean isDead()
    {
        return playerIsDead;
    }

    public float getStateTimer()
    {
        return stateTimer;
    }

    public int getVidas()
    {
        return vidas;
    }
    public int getLevel()
    {
        return level;
    }

}
