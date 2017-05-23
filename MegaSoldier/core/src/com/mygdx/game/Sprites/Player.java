package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ArraySelection;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MegaSoldier;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by Karla Rosas on 22/05/2017.
 */

public class Player extends Sprite {
    public enum State{ FALLING, JUMPING, STANNDING, RUNNING};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion playerStand;
    private Animation playerRun;
    private Animation playerJump;
    private float stateTimer;
    private boolean runnningRight;

    public Player(World world, PlayScreen screen)
    {
        super(screen.getAtlas().findRegion("sp4"));
        this.world = world;
        currentState = State.STANNDING;
        previousState = State.STANNDING;
        stateTimer = 0;
        runnningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 3; i++)
            frames.add(new TextureRegion(getTexture(), i * 192, 0, 192, 164 ));
        playerRun = new Animation(0.1f, frames);
        frames.clear();
        for(int i = 2; i < 3; i++)
            frames.add(new TextureRegion(getTexture(), i * 192, 0, 192, 164 ));
        playerJump = new Animation(0.1f, frames);
        frames.clear();


        playerStand = new TextureRegion(getTexture(), 389, 333, 192, 164);
        definePlayer();
        setBounds(0, 0, 33 / MegaSoldier.PPM, 33 / MegaSoldier.PPM);
        setRegion(playerStand);
    }

    public void update(float dt)
    {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt)
    {
        currentState = getState();

        TextureRegion region;
        switch (currentState){
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

        if((b2body.getLinearVelocity().x < 0 || !runnningRight) && !region.isFlipX())
        {
            region.flip(true, false);
            runnningRight = false;
        }
        else if((b2body.getLinearVelocity().x > 0 || runnningRight) && region.isFlipX())
        {
            region.flip(true, false);
            runnningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState()
    {
        if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        else if(b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANNDING;
    }

    public void definePlayer()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(35 / MegaSoldier.PPM,40 /MegaSoldier.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15/MegaSoldier.PPM);

        fdef.shape = shape;
        b2body.createFixture((fdef));

    }

}
