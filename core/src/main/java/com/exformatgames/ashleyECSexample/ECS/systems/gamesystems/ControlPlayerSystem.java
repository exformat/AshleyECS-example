package com.exformatgames.ashleyECSexample.ECS.systems.gamesystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.ashleyECSexample.ECS.components.InputComponent;
import com.exformatgames.ashleyECSexample.ECS.components.PlayerComponent;
import com.exformatgames.ashleyECSexample.ECS.components.SpriteComponent;
import com.exformatgames.ashleyECSexample.ECS.components.TransformComponent;

public class ControlPlayerSystem extends IteratingSystem {

    ComponentMapper<InputComponent> inputComponentComponentMapper = ComponentMapper.getFor(InputComponent.class);

    public ControlPlayerSystem() {
        super(Family.all(InputComponent.class, SpriteComponent.class, PlayerComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        InputComponent inputComponent = inputComponentComponentMapper.get(entity);



        if ( ! inputComponent.clickPosition.isZero()) {
            TransformComponent transformComponent = getEngine().createComponent(TransformComponent.class);

            transformComponent.position.set(inputComponent.clickPosition);
            entity.add(transformComponent);
        }
    }
}
