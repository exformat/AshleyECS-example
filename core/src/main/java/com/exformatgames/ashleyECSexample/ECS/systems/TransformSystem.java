package com.exformatgames.ashleyECSexample.ECS.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.ashleyECSexample.ECS.components.SpriteComponent;
import com.exformatgames.ashleyECSexample.ECS.components.TransformComponent;

public class TransformSystem extends IteratingSystem {

    //с помощью мапперов достаем компоненты из сущности
    //можно и SpriteComponent component = entity.getComponent(SpriteComponent.class), но это дольше
    ComponentMapper<SpriteComponent> spriteComponentComponentMapper = ComponentMapper.getFor(SpriteComponent.class);
    ComponentMapper<TransformComponent> transformComponentMapper = ComponentMapper.getFor(TransformComponent.class);


    //Family.all собирает сущности в которых есть все перечисленные компоненты
    //Family.one собирает сущности в которых есть хоть один из перечисленных компоненты
    //Family.exclude собирает сущности в которых нет перечисленные компоненты
    public TransformSystem() {
        super(Family.all(TransformComponent.class, SpriteComponent.class).get());
    }

    //цикл по всем сущностям из фильтра
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = transformComponentMapper.get(entity);
        SpriteComponent spriteComponent = spriteComponentComponentMapper.get(entity);

        spriteComponent.sprite.setPosition(transformComponent.position.x, transformComponent.position.y);
        spriteComponent.sprite.setRotation(transformComponent.angle);

        entity.remove(TransformComponent.class);
    }
}
