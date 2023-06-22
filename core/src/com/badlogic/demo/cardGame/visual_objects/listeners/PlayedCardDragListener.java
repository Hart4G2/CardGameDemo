package com.badlogic.demo.cardGame.visual_objects.listeners;

import com.badlogic.demo.cardGame.CardGameScreen;
import com.badlogic.demo.cardGame.visual_objects.CardDropZone;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class PlayedCardDragListener extends DragListener{
    private final Vector2 originalPos = new Vector2();
    private final Vector2 dragStartPos = new Vector2();
    private float originalRotation;
    private int originalZIndex;
    private final float duration = 0.2f;
    private final CardDropZone dropZone = CardGameScreen.getDropZone();

    @Override
    public void dragStart(InputEvent event, float x, float y, int pointer) {
        Actor target = event.getTarget();

        dragStartPos.set(event.getStageX(), event.getStageY());
        originalPos.set(target.getX(), target.getY());
        originalRotation = target.getRotation();

        originalZIndex = target.getZIndex();
        target.setZIndex(1000);
    }

    @Override
    public void drag(InputEvent event, float x, float y, int pointer) {
        Actor target = event.getTarget();

        float deltaX = event.getStageX() - dragStartPos.x;
        float deltaY = event.getStageY() - dragStartPos.y;
        target.moveBy(deltaX, deltaY);
        dragStartPos.set(event.getStageX(), event.getStageY());

        float rotationAngle = MathUtils.clamp(deltaX, -70f, 70f);

        target.setRotation(Interpolation.smooth.apply(originalRotation, rotationAngle, 0.2f));
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer) {
        Actor target = event.getTarget();

        target.setRotation(originalRotation);

        if (dropZone.isInDropZone(event.getStageX(), event.getStageY()))
            animateToDropZone(target);
        else
            animateToLastPosition(target);

        setScale(target, 0.5f, 0.5f);

        target.setZIndex(originalZIndex);
    }

    private void animateToDropZone(Actor target) {
        float x = MathUtils.clamp(target.getX(), Gdx.graphics.getWidth() / 6f, Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 6f);
        float y = MathUtils.clamp(target.getY(), Gdx.graphics.getHeight() / 4f, Gdx.graphics.getHeight() / 4f * 3f);

        target.addAction(
                Actions.sequence(
                        Actions.moveTo(x + 2, y + 6, duration / 2f, Interpolation.sineOut),
                        Actions.rotateTo(-1, duration / 10f),
                        Actions.moveTo(x - 1, y - 4, duration / 7f, Interpolation.sineIn),
                        Actions.moveTo(x, y, duration / 8f, Interpolation.sineOut),
                        Actions.rotateTo(originalRotation, duration / 10f)
                )
        );
    }

    private void animateToLastPosition(Actor target) {
        target.addAction(Actions.moveTo(originalPos.x, originalPos.y, duration, Interpolation.sineOut));
    }

    private void setScale(Actor target, float x, float y) {
        target.addAction(Actions.scaleTo(x, y, duration / 2f, Interpolation.sineOut));
    }
}
