package com.badlogic.demo.cardGame.visual_objects.listeners;

import com.badlogic.demo.cardGame.CardGameScreen;
import com.badlogic.demo.cardGame.visual_objects.CardDropZone;
import com.badlogic.demo.cardGame.visual_objects.CardImage;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class HeroCardDragListener extends DragListener {
    private final Vector2 originalPos = new Vector2();
    private final Vector2 dragStartPos = new Vector2();
    private float originalRotation;
    private final float duration = 0.2f;
    private final CardDropZone dropZone = CardGameScreen.getDropZone();
    private int originalZIndex = 1;

    //todo исправить баг с перемещением, может запрещать перемещение, когда происходит анимация

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);

        Actor target = event.getTarget();

        setScale(target, 0.8f, 0.8f);

        originalZIndex = target.getZIndex();
        target.setZIndex(1000);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        super.exit(event, x, y, pointer, toActor);

        Actor target = event.getTarget();

        setScale(target, 0.6f, 0.6f);
        target.setZIndex(originalZIndex);
    }

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

        float rotationAngle = deltaX;
        rotationAngle = MathUtils.clamp(rotationAngle, -70f, 70f);

        target.setRotation(Interpolation.smooth.apply(originalRotation, rotationAngle, 0.2f));

        if (dropZone.isInDropZone(event.getStageX(), event.getStageY())) {
            setScale(target, 0.5f, 0.5f);
        } else {
            setScale(target, 0.8f, 0.8f);
        }
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer) {
        event.getTarget().setRotation(originalRotation);

        if (dropZone.isInDropZone(event.getStageX(), event.getStageY())) {
            ((CardImage) event.getTarget()).startReleaseAnimation();
            setScale(event.getTarget(), 0.5f, 0.5f);
        } else {
            animateToLastPosition(event.getTarget());
            setScale(event.getTarget(), 0.6f, 0.6f);
        }

        event.getTarget().setZIndex(originalZIndex);
    }

    private void setScale(Actor target, float x, float y) {
        target.addAction(Actions.scaleTo(x, y, duration / 2f, Interpolation.sineOut));
    }

    private void animateToLastPosition(Actor target) {
        target.addAction(Actions.moveTo(originalPos.x, originalPos.y, duration, Interpolation.sineOut));
    }
}
