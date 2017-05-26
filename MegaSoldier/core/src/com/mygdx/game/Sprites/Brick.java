package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MegaSoldier;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by Karla Rosas on 22/05/2017.
 */

public class Brick extends InteractiveTiledObject {

    public Brick(PlayScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MegaSoldier.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick", "Collision");
        setCategoryFilter(MegaSoldier.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(200);
        MegaSoldier.manager.get("Audio/Music/breakblock.wav", Music.class).play();

    }
}
