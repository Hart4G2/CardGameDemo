package com.badlogic.demo.cardGame;

import com.badlogic.demo.Assets;
import com.badlogic.demo.Game;
import com.badlogic.demo.cardGame.Objects.Card;
import com.badlogic.demo.cardGame.Objects.CardDescription;
import com.badlogic.demo.cardGame.Objects.Effect;
import com.badlogic.demo.cardGame.visual_objects.CardDropZone;
import com.badlogic.demo.cardGame.visual_objects.CharacterActor;
import com.badlogic.demo.cardGame.visual_objects.DeckActor;
import com.badlogic.demo.cardGame.visual_objects.HandOfCards;
import com.badlogic.demo.cardGame.visual_objects.HeroCardImage;
import com.badlogic.demo.cardGame.visual_objects.StatsActor;
import com.badlogic.demo.cardGame.visual_objects.listeners.HeroCardDragListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardGameScreen implements Screen {
    private final Game game;
    private final SpriteBatch batch;
    private final Stage stage;
    private OrthographicCamera camera;
    private float elapsedTime;
    public static Assets assets;

    public static HandOfCards heroHand;
    public static HandOfCards opponentHand;

    private CharacterActor heroActor;
    private CharacterActor opponentActor;

    private DeckActor heroDeckActor;
    private DeckActor opponentDeckActor;

    private StatsActor heroStats;
    private StatsActor opponentStats;

    private static CardDropZone dropZone;

    public CardGameScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        stage = new Stage();
        assets = Game.assets;
        Gdx.input.setInputProcessor(stage);

        // Setting background
        Texture texture = new Texture(Gdx.files.internal("bg.png"));
        Image bg = new Image(texture);
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(bg);


        // Setting camera and origin axes to left downer corner
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.getViewport().setCamera(camera);

        // Setting dropZone for cards
        dropZone = new CardDropZone(stage);
        stage.addActor(dropZone);

        // Setting Hero Actor
        Texture heroTexture = new Texture(Gdx.files.internal("hero.png"));
        heroActor = new CharacterActor(stage,
                heroTexture,
                "Hermione Granger",
                assets.skin,
                true);
        stage.addActor(heroActor);

        // Setting Opponent Actor
        Texture opponentTexture = new Texture(Gdx.files.internal("opponent.png"));
        opponentActor = new CharacterActor(stage,
                opponentTexture,
                "Future bride",
                assets.skin,
                false);
        stage.addActor(opponentActor);

        // Setting stats for characters
        heroStats = new StatsActor(5, 5, assets.skin, true);
        stage.addActor(heroStats);

        heroStats.setHealthElapsed(2);
        heroStats.setManaElapsed(2);

        opponentStats = new StatsActor(6, 3, assets.skin, false);
        stage.addActor(opponentStats);

        opponentStats.setHealthElapsed(2);
        opponentStats.setManaElapsed(2);

        elapsedTime = 0;
    }

    @Override
    public void show() {
        initCards();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        elapsedTime += delta;

        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }

    public static CardDropZone getDropZone() {
        return dropZone;
    }

    private void initCards() {
        List<Integer> props = new ArrayList<>();

        props.add(5);
        CardDescription cardDescriptionWith5Dmg =
                new CardDescription("Наносит 5 ед. урона", new ArrayList<>(props), Effect.DAMAGE);
        props.clear();

        props.add(3);
        CardDescription cardDescriptionWith5armor =
                new CardDescription("Добавляет 3 ед. брони", new ArrayList<>(props), Effect.ARMOR);
        props.clear();

        props.add(2);
        CardDescription cardDescriptionWith2stamina =
                new CardDescription("Добавляет 2 ед. выносливости", new ArrayList<>(props), Effect.BONUS_STAMINA);
        props.clear();


        Texture texture = new Texture(Gdx.files.internal("card.png"));

        List<Card> heroCards = Arrays.asList(
                new Card(texture, cardDescriptionWith5Dmg, 3, false, true),
                new Card(texture, cardDescriptionWith5armor, 3, false, true),
                new Card(texture, cardDescriptionWith2stamina, 3, true, true));

        List<Card> opponentCards = Arrays.asList(
                new Card(texture, cardDescriptionWith5Dmg, 3, false, false),
                new Card(texture, cardDescriptionWith2stamina, 3, true, false));

        for(Card card : heroCards){
            HeroCardImage cardImage = (HeroCardImage) card.getCardImage();
            cardImage.setDragListener();
        }

        heroHand = new HandOfCards(heroCards, true, stage);

        opponentHand = new HandOfCards(opponentCards, false, stage);

        List<Card> opponentDeckCards = Arrays.asList(
                new Card(texture, cardDescriptionWith5Dmg, 3, false, false),
                new Card(texture, cardDescriptionWith2stamina, 3, true, false));

        List<Card> heroDeckCards = Arrays.asList(
                new Card(texture, cardDescriptionWith5Dmg, 3, false, true),
                new Card(texture, cardDescriptionWith5armor, 3, false, true),
                new Card(texture, cardDescriptionWith2stamina, 3, true, true));

        heroDeckActor = new DeckActor(heroDeckCards, true, stage);

        opponentDeckActor = new DeckActor(opponentDeckCards, false, stage);
    }
}
