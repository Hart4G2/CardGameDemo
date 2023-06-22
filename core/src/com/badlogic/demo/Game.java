package com.badlogic.demo;


import com.badlogic.demo.cardGame.CardGameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {

	public SpriteBatch batch;
	public BitmapFont font;
	public static Assets assets;

	@Override
	public void create() {
		assets = new Assets();
		assets.loadAllAssets();

		batch = new SpriteBatch();
		font = assets.font;

		//todo fullscreen
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

		this.setScreen(new CardGameScreen(this));
	}

	@Override
	public void resize(int width, int height) {

		//todo resizable screen

		super.resize(width, height);
		if(getScreen() != null){
			getScreen().resize(width, height);
		}
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}