package com.exformatgames.ashleyECSexample.ECS.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.exformatgames.ashleyECSexample.ECS.components.InputComponent;

import static com.badlogic.gdx.Gdx.*;

public class InputSystem extends IteratingSystem {
    ComponentMapper<InputComponent> inputComponentComponentMapper = ComponentMapper.getFor(InputComponent.class);
    public Vector2 clickPosition = new Vector2();
    Viewport viewport;

    public InputSystem(Viewport viewport) {
        super(Family.all(InputComponent.class).get());
        this.viewport = viewport;
    }

    @Override
    public void startProcessing() {
        if (input.isButtonPressed(Input.Buttons.LEFT)) {
            clickPosition.set(input.getX(), input.getY());
        }

        viewport.unproject(clickPosition);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        InputComponent inputComponent = inputComponentComponentMapper.get(entity);

        if ( ! clickPosition.isZero()) {
            inputComponent.clickPosition.set(clickPosition.x, clickPosition.y);
        }
    }

    @Override
    public void endProcessing() {
        clickPosition.setZero();
    }
}
