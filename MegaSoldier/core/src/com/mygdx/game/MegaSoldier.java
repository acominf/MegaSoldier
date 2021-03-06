package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.CreditScreen;
import com.mygdx.game.Screens.GameOverScreen;
import com.mygdx.game.Screens.HelpScreen;
import com.mygdx.game.Screens.MenuScreen;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Screens.WinScreen;

public class MegaSoldier extends Game {

	public static final int V_WIDTH = 268;
	public static final int V_HEIGHT = 200;
	public static final float PPM = 100;

	public static final short NOTHING_BIT = 0;
	public static final short GROUND_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short OBJETO_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short ENEMY_HEAD_BIT = 128;
	public static final short PIPE_BIT = 128;


	public SpriteBatch batch;

	public PlayScreen playScreen;
	public MenuScreen menuScreen;
	public CreditScreen creditScreen;
	public GameOverScreen gameoverScreen;
	public HelpScreen helpScreen;
	public WinScreen winScreen;


	public static AssetManager manager;

    public AssetManager getManager(){
        return manager;
    }


    @Override
	public void create() {

		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("Audio/Music/soundsGame.wav", Music.class);
		manager.load("Audio/Music/breakblock.wav", Music.class);
		manager.load("Audio/Music/bump.wav", Music.class);
		manager.load("Audio/Music/enemyDied.wav", Music.class);
        manager.load("logo.png", Texture.class);
		manager.load("creditos.png", Texture.class);
		manager.load("gameover.png", Texture.class);
		manager.load("ayuda.png", Texture.class);
		manager.load("win.png", Texture.class);
		manager.finishLoading();

		playScreen = new PlayScreen(this);
		menuScreen = new MenuScreen(this);
		creditScreen = new CreditScreen(this);
		gameoverScreen = new GameOverScreen(this);
		helpScreen = new HelpScreen(this);
		winScreen = new WinScreen(this);

		setScreen(menuScreen);

	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
	}

	@Override
	public void render() {
		super.render();
	}
}


