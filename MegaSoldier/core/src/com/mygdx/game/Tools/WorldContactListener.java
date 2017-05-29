package com.mygdx.game.Tools;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.MegaSoldier;
import com.mygdx.game.Sprites.Enemy;
import com.mygdx.game.Sprites.InteractiveTiledObject;

/**
 * Created by Jonathan on 23/05/2017.
 */

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA =contact.getFixtureA();
        Fixture fixB =contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        if (fixA.getUserData() == "head" || fixB.getUserData() == "head")
        {
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head  == fixA ? fixB : fixA;

            if (object.getUserData() != null && InteractiveTiledObject.class.isAssignableFrom(object.getUserData().getClass()))
            {
                ((InteractiveTiledObject) object.getUserData()).onHeadHit();
            }
        }

        switch (cDef){
            case MegaSoldier.ENEMY_HEAD_BIT | MegaSoldier.PLAYER_BIT:
                if (fixA.getFilterData().categoryBits == MegaSoldier.ENEMY_HEAD_BIT)
                    ((Enemy)fixA.getUserData()).hitOnHead();
                else
                    ((Enemy)fixB.getUserData()).hitOnHead();
                break;
            case MegaSoldier.ENEMY_BIT | MegaSoldier.BRICK_BIT:
                if (fixA.getFilterData().categoryBits == MegaSoldier.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case MegaSoldier.ENEMY_BIT | MegaSoldier.GROUND_BIT:
                if (fixA.getFilterData().categoryBits == MegaSoldier.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case MegaSoldier.PLAYER_BIT | MegaSoldier.ENEMY_BIT:
                Gdx.app.log("Player", "Died");
                break;
            case MegaSoldier.ENEMY_BIT | MegaSoldier.ENEMY_BIT:
                ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;

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
}
