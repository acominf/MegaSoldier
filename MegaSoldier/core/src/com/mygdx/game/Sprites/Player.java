package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MegaSoldier;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by Karla Rosas on 22/05/2017.
 */

public class Player extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion playerStand;

    public Player(World world, PlayScreen screen)
    {
        super(screen.getAtlas().findRegion("s1"));
        this.world = world;
        definePlayer();
        playerStand = new TextureRegion(getTexture(), 1, 333, 192, 164);
        setBounds(0, 0, 33 / MegaSoldier.PPM, 33 / MegaSoldier.PPM);
        setRegion(playerStand);
    }

    public void update(float dt)
    {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
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
