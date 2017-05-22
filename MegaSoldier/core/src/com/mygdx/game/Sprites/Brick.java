package com.mygdx.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Karla Rosas on 22/05/2017.
 */

public class Brick extends InteractiveTiledObject {

    public Brick(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
    }
}
