package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MegaSoldier;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by Karla Rosas on 22/05/2017.
 */

public class Objeto extends InteractiveTiledObject{
    private static TiledMapTileSet tileSet;
    private final int BLANK_OBJECT = 94;

    public Objeto(PlayScreen screen, Rectangle bounds)
    {
        super(screen, bounds);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(MegaSoldier.OBJETO_BIT);


    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Objeto", "Collision");
        getCell().setTile(tileSet.getTile(BLANK_OBJECT));
        Hud.addScore(500);
        MegaSoldier.manager.get("Audio/Music/bump.wav", Music.class).play();
    }
}
