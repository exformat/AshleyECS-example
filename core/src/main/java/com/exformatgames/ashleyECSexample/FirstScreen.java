package com.exformatgames.ashleyECSexample;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.exformatgames.ashleyECSexample.ECS.components.InputComponent;
import com.exformatgames.ashleyECSexample.ECS.components.PlayerComponent;
import com.exformatgames.ashleyECSexample.ECS.components.SpriteComponent;
import com.exformatgames.ashleyECSexample.ECS.systems.InputSystem;
import com.exformatgames.ashleyECSexample.ECS.systems.RenderSystem;
import com.exformatgames.ashleyECSexample.ECS.systems.TransformSystem;
import com.exformatgames.ashleyECSexample.ECS.systems.gamesystems.ControlPlayerSystem;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    Viewport viewport;
    SpriteBatch spriteBatch;
    PooledEngine engine;


    @Override
    public void show() {
        viewport = new FitViewport(800, 600);
        spriteBatch = new SpriteBatch();
        engine = new PooledEngine();

        //очередность добавления систем важна
        //можно менять очередность выполнения систем в конструкторе самой системы
        engine.addSystem(new InputSystem(viewport));
        engine.addSystem(new TransformSystem());
        engine.addSystem(new RenderSystem(spriteBatch));
        engine.addSystem(new ControlPlayerSystem());

        //создавать компоненты можно и через new, но лучше через пулл.
        SpriteComponent playerSpriteComponent = engine.createComponent(SpriteComponent.class);
        InputComponent inputComponent = engine.createComponent(InputComponent.class);

        playerSpriteComponent.sprite = new Sprite(new Texture("star.png"));

        //с сущностью то-же что и с компонентами
        Entity playerEntity = engine.createEntity();

        //добавление компонентов в сущность
        playerEntity.add(playerSpriteComponent);
        playerEntity.add(inputComponent);
        playerEntity.add(engine.createComponent(PlayerComponent.class));

        //добавление сущности в движку
        engine.addEntity(playerEntity);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}
