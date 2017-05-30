package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MegaSoldier;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Screens.PlayScreen;
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

public class Pipe extends InteractiveTiledObject {

    public Pipe(PlayScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MegaSoldier.PIPE_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Pipe", "Collision");
        setCategoryFilter(MegaSoldier.DESTROYED_BIT);
        getCell().setTile(null);
    }
}