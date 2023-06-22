package com.badlogic.demo.mainmenu;

import static com.badlogic.demo.Game.assets;

import com.badlogic.demo.Game;
import com.badlogic.demo.cardGame.CardGameScreen;
import com.badlogic.demo.comic.ComicScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    final Game game;

    Stage stage;

    Skin tableSkin;

    TextButton.TextButtonStyle buttonStyle;

    public MainMenuScreen(final Game game) {
        this.game = game;
        //todo fullscreen
//        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

        initButtonStyle();
        initTableSkin();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // Create a Table
        Table table = new Table(tableSkin);
        table.setPosition(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight() / 2);

        // Create Buttons
        TextButton btnContinue = new TextButton("Continue", buttonStyle);
        TextButton btnNewGame = new TextButton("New Game", buttonStyle);
        TextButton btnSettings = new TextButton("Settings", buttonStyle);
        TextButton btnExit = new TextButton("Exit", buttonStyle);

        //Put buttons to the table
        table.add(btnContinue).pad(10).row();
        table.add(btnNewGame).pad(10).row();
        table.add(btnSettings).pad(10).row();
        table.add(btnExit).pad(10).row();

        //Listeners for buttons
        btnContinue.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                continueClicked();
            }
        });

        btnNewGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                newGameClicked();
            }
        });

        btnSettings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsClicked();
            }
        });

        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitClicked();
            }
        });

        //Create background image and adding it to stage.
        //todo set normal image
        Image bgImage = new Image(new TextureRegionDrawable(new TextureRegion(new Texture("mainmenu/background.png"))));

        stage.addActor(bgImage);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        stage.act(delta);
        stage.draw();
    }

    private void continueClicked() {
        //todo continue game on the last checkpoint
        game.setScreen(new CardGameScreen(game));
    }

    private void newGameClicked() {
        game.setScreen(new ComicScreen(game, 1));
    }

    private void settingsClicked() {
        //todo open settings window
    }

    private void exitClicked() {
        Gdx.app.exit();
    }

    private void initButtonStyle(){
        buttonStyle = new TextButton.TextButtonStyle();

        buttonStyle.font = new BitmapFont();

        buttonStyle.up = new TextureRegionDrawable(assets.mainButtonAtlas.findRegion("button-up"));
        buttonStyle.down = new TextureRegionDrawable(assets.mainButtonAtlas.findRegion("button-down"));
        buttonStyle.over = new TextureRegionDrawable(assets.mainButtonAtlas.findRegion("button-hover"));
    }

    private void initTableSkin(){
        tableSkin = new Skin();

        Label.LabelStyle defaultLabelStyle = new Label.LabelStyle();

        defaultLabelStyle.font = new BitmapFont();

        tableSkin.add("default", defaultLabelStyle);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
