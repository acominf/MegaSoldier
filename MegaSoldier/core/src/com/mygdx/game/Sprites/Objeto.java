package com.mygdx.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MegaSoldier;

/**
 * Created by Karla Rosas on 22/05/2017.
 */

public class Objeto extends InteractiveTiledObject{

    public Objeto(World world, TiledMap map, Rectangle bounds)
    {
        super(world, map, bounds);


    }
}
