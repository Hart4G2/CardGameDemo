package com.badlogic.demo.cardGame.visual_objects;

import com.badlogic.demo.cardGame.CardGameScreen;
import com.badlogic.demo.cardGame.Objects.Card;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;

import java.util.List;

public class DeckActor extends Group {

    private List<Card> cards;
    private Image image;

    private final boolean isHero;
    private float intent;
    public DeckActor(final List<Card> cards, boolean isHero, Stage stage) {
        this.cards = cards;
        this.isHero = isHero;

        image = new Image(new Texture(Gdx.files.internal("deck.png")));

        intent = 40f;

        float imageWidth = image.getWidth() * 0.48f;
        float imageHeight = image.getHeight() * 0.52f;

        float x = isHero ? Gdx.graphics.getWidth() - imageWidth - intent : intent;
        float y = isHero ? intent : Gdx.graphics.getHeight() - imageHeight - intent;

        setPosition(x, y);

        image.setBounds(getX(), getY(), imageWidth, imageHeight);
        stage.addActor(image);

        setZIndex(1);

        layoutCards(stage);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                takeCard(DeckActor.this.cards.get(cards.size() - 1));
            }
        }, 1);
    }

    public void takeCard(final Card card){
        final CardImage cardImage = card.getCardImage();

        /* todo
            1. Карта повляется из колоды
            2. Карта переворачивается
            3. Отображается попап окно с картой
            4. Карты в руке сдвигаются и новая карта перемещается на место, на котором она должна быть
         */

        final float duration = 0.2f;
        final int originalZIndex = cardImage.getZIndex();

        cardImage.addAction(Actions.sequence(
                Actions.scaleTo(0.6f, 0.6f, duration, Interpolation.circleOut),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        cardImage.setZIndex(1000);
                        if (isHero) {
                            cardImage.addAction(Actions.sequence(
                                    Actions.scaleTo(0.3f,0.6f, duration, Interpolation.elasticIn),
                                    Actions.run(new Runnable() {
                                        @Override
                                        public void run() {
                                            card.setFrontSide();
                                        }
                                    }),
                                    Actions.scaleTo(0.6f,0.6f, duration, Interpolation.elasticOut)
                            ));
                        }
                    }
                }),
                Actions.moveTo(cardImage.getX(), cardImage.getY(), duration * 2f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        cardImage.setZIndex(originalZIndex);
                    }
                }),
                Actions.moveTo(cardImage.getX(), cardImage.getY(), duration * 2f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        HandOfCards handOfCards = isHero ? CardGameScreen.heroHand : CardGameScreen.opponentHand;
                        handOfCards.addCard(card);
                        if(isHero){
                            ((HeroCardImage)card.getCardImage()).setDragListener();
                        }
                    }
                })
        ));
    }

    private void layoutCards(Stage stage) {
        float x = getX() + image.getWidth() / 8f;
        float y = isHero ? getY() + image.getHeight() / 16f :
                Gdx.graphics.getHeight() - image.getHeight() / 16f - intent;

        for (Card card : cards) {
            card.getCardImage().setPosition(x, y);
            card.setBackSide();
            card.getCardImage().clearListeners();

            if(!isHero) {
                card.getCardImage().setScale(0.5f, -0.5f);
            } else {
                card.getCardImage().setScale(0.5f, 0.5f);
            }

            stage.addActor(card.getCardImage());
        }
    }
}
