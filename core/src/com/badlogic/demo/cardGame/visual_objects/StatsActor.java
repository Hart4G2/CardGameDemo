package com.badlogic.demo.cardGame.visual_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.List;

public class StatsActor extends Group {

    private Table manaTable;
    private Image healthImage;
    private Label healthLabel;

    private List<Image> manaImages;

    private Skin skin;

    private int mana;
    private int health;

    public StatsActor(int mana, int health, Skin skin, boolean isHero) {
        this.skin = skin;
        this.mana = mana;
        this.health = health;

        setBounds(isHero);

        Image image = new Image(new Texture(Gdx.files.internal("stats.png")));
        image.setBounds(getX(), getY(), getWidth(), getHeight());

        addActor(image);

        float width = 40;
        float height = 35;

        setManaTable(width, height);
        setHealthStat(width, height);

        addActor(healthImage);
        addActor(manaTable);

        addActor(healthLabel);
    }

    public void setManaElapsed(int manaElapsed) {
        mana -= manaElapsed;

        for(int i = manaImages.size()-1; i >= mana; i--){

            manaImages.get(i).setDrawable(new TextureRegionDrawable(
                    skin.get("mana_elapsed", Texture.class)));
        }
    }

    public void setHealthElapsed(int healthElapsed) {
        health -= healthElapsed;

        healthLabel.setText(Integer.toString(health));
    }

    private void setManaTable(float width, float height){
        manaTable = new Table();

        setManaImages();

        for (Image image : manaImages) {
            manaTable.add(image).width(width).height(height);
        }

        manaTable.setBounds(0, 0, width * manaImages.size(), height);
    }

    private void setHealthStat(float width, float height){
        Texture healthTexture = skin.get("health", Texture.class);
        healthImage = new Image(healthTexture);

        healthImage.setBounds(0, 50, width, height);

        healthLabel = new Label(Integer.toString(health), skin);
        healthLabel.setBounds(healthImage.getX() + 15,healthImage.getY() + 5, width - 2, height - 2);
    }

    private void setManaImages(){
        manaImages = new ArrayList<>();

        Texture manaTexture = skin.get("mana", Texture.class);

        for(int i = 0; i < mana; i++){
            Image manaImage = new Image(manaTexture);
            manaImages.add(manaImage);
        }
    }

    public void setBounds(boolean isHero){
        float indent = 20f;
        float width = Gdx.graphics.getWidth() / 8f - indent;
        float height = Gdx.graphics.getHeight() / 8f - indent;

        float posX, posY;
        if (isHero) {
            posX = Gdx.graphics.getWidth() * 3f / 4f - width + indent;
            posY = Gdx.graphics.getHeight() / 15f + indent;
        } else {
            posX = Gdx.graphics.getWidth() / 4f - indent;
            posY = Gdx.graphics.getHeight() - height - indent;
        }

        setBounds(posX, posY, width, height);
    }
}
