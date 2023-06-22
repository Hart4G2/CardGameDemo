package com.badlogic.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class Assets {
    public AssetManager assetManager;
    public TextureAtlas mainButtonAtlas;
    public Skin skin;

    public Assets() {
        assetManager = new AssetManager();
    }

    public BitmapFont font = new BitmapFont();

    public void loadAllAssets() {
        // Set up the asset manager and loader
        AssetManager assetManager = new AssetManager();

        // Queue up assets to be loaded
        assetManager.load("mainmenu/button-atlas.atlas", TextureAtlas.class);

        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(new InternalFileHandleResolver()));
        assetManager.load("ui/fonts/Viperine.ttf", FreeTypeFontGenerator.class);

        // Call update() on the main thread until all assets are loaded
        while (!assetManager.update()) {
            // Render loading screen or wait for some time
        }

        // Retrieve the loaded assets
        mainButtonAtlas = assetManager.get("mainmenu/button-atlas.atlas", TextureAtlas.class);
        FreeTypeFontGenerator generator = assetManager.get("ui/fonts/Viperine.ttf", FreeTypeFontGenerator.class);

        FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameter.size = 16;
        font = generator.generateFont(freeTypeFontParameter);

        skin = new Skin();

        Texture health = new Texture(Gdx.files.internal("cardgame/health.png"));
        skin.add("health", health);

        Texture mana = new Texture(Gdx.files.internal("cardgame/mana.png"));
        skin.add("mana", mana);

        Texture manaElapsed = new Texture(Gdx.files.internal("cardgame/mana_elapsed.png"));
        skin.add("mana_elapsed", manaElapsed);

        Texture healthElapsed = new Texture(Gdx.files.internal("cardgame/health_elapsed.png"));
        skin.add("health_elapsed", healthElapsed);

        skin.add("default", font, BitmapFont.class);
        skin.load(Gdx.files.internal("ui/skin.json"));
    }

//    public static void main (String[] args) {
//        TexturePacker.Settings settings = new TexturePacker.Settings();
//        settings.maxWidth = 2048;
//        settings.maxHeight = 2048;
//        settings.filterMin = Texture.TextureFilter.Linear;
//        settings.filterMag = Texture.TextureFilter.Linear;
//        TexturePacker.process(settings, "C:\\ProjectsJava\\DemoGDX\\assets\\mainmenu\\button", "C:\\ProjectsJava\\DemoGDX\\assets\\mainmenu\\button", "button-atlas.atlas");
//    }
}
