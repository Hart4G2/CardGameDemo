package com.badlogic.demo.cardGame.visual_objects;

import static com.badlogic.demo.Game.assets;

import com.badlogic.demo.cardGame.CardGameScreen;
import com.badlogic.demo.cardGame.visual_objects.listeners.HeroCardDragListener;
import com.badlogic.demo.cardGame.visual_objects.listeners.PlayedCardDragListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class HeroCardImage extends CardImage {
    private boolean isBackSide;
    private Texture texture;
    private final CardDropZone dropZone;

    private HeroCardDragListener heroCardDragListener;
    private PlayedCardDragListener playedCardDragListener;

    public HeroCardImage(Texture texture) {
        super(texture);
        this.texture = texture;

        this.dropZone = CardGameScreen.getDropZone();
    }

    @Override
    public void setFrontSide(){
        Texture texture = new Texture(Gdx.files.internal("card.png"));

        this.setDrawable(new TextureRegionDrawable(texture));
        isBackSide = false;
    }

    @Override
    public void setBackSide(){
        Texture texture = new Texture(Gdx.files.internal("cardbackside.png"));

        this.setDrawable(new TextureRegionDrawable(texture));
        isBackSide = true;
    }

    @Override
    public void startReleaseAnimation() {
        if(CardPopup.isOpened){
            CardPopup.currentPopup.hide();
        }
        new CardPopup(texture, assets.skin).show(this.getStage());

        CardGameScreen.heroHand.removeCard(this);

        dropZone.addCard(this);

        removeListener(heroCardDragListener);

        setPlayedDragListener();

        releaseAnimation();
    }

    @Override
    public boolean isOnBackSide() {
        return isBackSide;
    }

    public void setDragListener() {
        if(heroCardDragListener != null){
            removeListener(heroCardDragListener);
        }

        heroCardDragListener = new HeroCardDragListener();
        addListener(heroCardDragListener);
    }

    public void setPlayedDragListener(){
        if(playedCardDragListener != null){
            removeListener(playedCardDragListener);
        }

        playedCardDragListener = new PlayedCardDragListener();
        addListener(playedCardDragListener);
    }

    private void releaseAnimation(){
        float margin = 20f * dropZone.getChildren().size;
        float x = (Gdx.graphics.getWidth() * 9.25f / 10f) / 2f + margin;
        float y = (Gdx.graphics.getHeight() * 9f / 10f) / 2f - margin;
        float duration = 0.2f;
        float originalRotation = getRotation();

        addAction(
                Actions.sequence(
                        Actions.moveTo(x + 2, y + 6, duration / 2f, Interpolation.sineOut),
                        Actions.rotateTo(-1, duration / 10f),
                        Actions.moveTo(x - 1, y - 4, duration / 7f, Interpolation.sineIn),
                        Actions.moveTo(x, y, duration / 8f, Interpolation.sineOut),
                        Actions.rotateTo(originalRotation, duration / 10f)
                )
        );
    }
}
