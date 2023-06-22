package com.badlogic.demo.cardGame.visual_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class CardImage extends Image {

    public CardImage(Texture texture){
        super(texture);
    }

    public abstract boolean isOnBackSide();

    public abstract void setFrontSide();

    public abstract void setBackSide();

    public abstract void startReleaseAnimation();
}
