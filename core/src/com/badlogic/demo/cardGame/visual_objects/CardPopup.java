package com.badlogic.demo.cardGame.visual_objects;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;

public class CardPopup extends Dialog {

    public static boolean isOpened;
    public static CardPopup currentPopup;

    public CardPopup(Texture texture, Skin skin) {
        super("", skin);
        setPosition(Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() / 2f);
        setModal(false);

        Image image = new Image(texture);
        getContentTable().add(image).padRight(Gdx.graphics.getWidth() / 2f).width(image.getWidth()).height(image.getHeight()).row();

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                hide(fadeOut(0.3f, Interpolation.fade));
                isOpened = false;
            }
        });

        currentPopup = this;
    }

    @Override
    public Dialog show(Stage stage) {
        isOpened = true;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                hide(fadeOut(0.3f, Interpolation.fade));
                isOpened = false;
            }
        }, 3);
        return super.show(stage);
    }
}
