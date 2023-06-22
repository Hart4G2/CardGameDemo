package com.badlogic.demo.cardGame.visual_objects;

import static com.badlogic.demo.Game.assets;

import com.badlogic.demo.cardGame.CardGameScreen;
import com.badlogic.demo.cardGame.visual_objects.listeners.PlayedCardDragListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class OpponentCardImage extends CardImage {
    private boolean isBackSide;
    private final CardDropZone dropZone;
    private Texture texture;
    private PlayedCardDragListener playedCardDragListener;

    public OpponentCardImage(Texture texture){
        super(texture);
        this.texture = texture;

        setBackSide();

        this.dropZone = CardGameScreen.getDropZone();

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                startReleaseAnimation();
            }
        });
    }

    @Override
    public boolean isOnBackSide() {
        return isBackSide;
    }

    @Override
    public void setFrontSide(){
        setDrawable(new TextureRegionDrawable(texture));
        isBackSide = false;
    }

    @Override
    public void setBackSide(){
        Texture texture = new Texture(Gdx.files.internal("cardbackside.png"));

        setDrawable(new TextureRegionDrawable(texture));
        isBackSide = true;
    }

    @Override
    public void startReleaseAnimation() {

        if(CardPopup.isOpened){
            CardPopup.currentPopup.hide();
        }
        new CardPopup(texture, assets.skin).show(this.getStage());

        releaseAnimation();

        CardGameScreen.opponentHand.removeCard(this);

        dropZone.addCard(this);

        setPlayedDragListener();
    }

    private void releaseAnimation(){
        float centerX = Gdx.graphics.getWidth() / 2f;
        float CARD_WIDTH = Gdx.graphics.getWidth() / 20f;

        float margin = 20f * dropZone.getChildren().size;
        float x = (Gdx.graphics.getWidth() * 9.25f / 10f) / 2f + margin;
        float y = (Gdx.graphics.getHeight() * 9f / 10f) / 2f - margin;

        final int originalZIndex = getZIndex();

        float duration = 0.2f;
        addAction(Actions.sequence(
                Actions.moveTo(centerX - CARD_WIDTH * 5f / 6f, this.getY() - 20,
                        duration * 4f, Interpolation.sineOut),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        OpponentCardImage.this.setZIndex(1000);
                    }
                }),
                Actions.moveTo(x, y, duration * 4f, Interpolation.sineOut),
                Actions.scaleTo(0.5f, 0.5f, duration / 2f, Interpolation.circleOut),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        OpponentCardImage.this.setZIndex(originalZIndex);
                        if(isBackSide){
                            OpponentCardImage.this.setFrontSide();
                        }
                    }
                })
        ));
    }

    public void setPlayedDragListener(){
        if(playedCardDragListener != null){
            removeListener(playedCardDragListener);
        }

        playedCardDragListener = new PlayedCardDragListener();
        addListener(playedCardDragListener);
    }
}
