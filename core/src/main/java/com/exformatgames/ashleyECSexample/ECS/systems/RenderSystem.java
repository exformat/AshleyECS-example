package com.exformatgames.ashleyECSexample.ECS.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exformatgames.ashleyECSexample.ECS.components.SpriteComponent;

public class RenderSystem extends IteratingSystem {

    ComponentMapper<SpriteComponent> componentMapper = ComponentMapper.getFor(SpriteComponent.class);
    SpriteBatch spriteBatch;


    public RenderSystem(SpriteBatch spriteBatch) {
        super(Family.all(SpriteComponent.class).get());
        this.spriteBatch = spriteBatch;
    }



    @Override
    public void startProcessing() {
        spriteBatch.begin();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SpriteComponent spriteComponent = componentMapper.get(entity);

        spriteComponent.sprite.draw(spriteBatch);
    }

    @Override
    public void endProcessing() {
        spriteBatch.end();
    }
}
