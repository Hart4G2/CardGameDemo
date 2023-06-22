package com.badlogic.demo.cardGame.visual_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CharacterActor extends Actor {
    private Image image;
    private Label nameLabel;

    public CharacterActor(Stage stage, Texture texture, String name, Skin skin, boolean isHero) {
        this.image = new Image(texture);
        this.nameLabel = new Label(name, skin, "default");

        setBounds(isHero);

        addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                nameLabel.setVisible(true);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                nameLabel.setVisible(false);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setTouchable(Touchable.disabled);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setTouchable(Touchable.enabled);
            }
        });

        layoutObjects(stage);
    }

    private void setBounds(boolean isHero){
        float x = isHero ? 20 : Gdx.graphics.getWidth() * 7f / 8f - 20;
        float y = isHero ? 20 : Gdx.graphics.getHeight() / 2f - 20;

        setBounds(x, y, Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 2f);
    }

    private void layoutObjects(Stage stage){
        nameLabel.setVisible(false);
        image.setBounds(getX(), getY(), Gdx.graphics.getWidth() / 8f, Gdx.graphics.getHeight() / 2f);

        stage.addActor(image);
        stage.addActor(nameLabel);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (nameLabel.isVisible()) {
            nameLabel.setPosition(Gdx.input.getX() - nameLabel.getText().length * 3f, Gdx.graphics.getHeight() - Gdx.input.getY());
        }
    }

    public void setNameLabel(String text){
        this.nameLabel.setText(text);
    }

    public void setImage(Texture texture){
        this.image.setDrawable(new TextureRegionDrawable(texture));
    }
}
