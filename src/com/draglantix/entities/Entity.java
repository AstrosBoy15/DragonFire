package com.draglantix.entities;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.draglantix.assets.PlayAssets;
import com.draglantix.collsion.Collisions;
import com.draglantix.collsion.Polygon;
import com.draglantix.engine.Engine;
import com.draglantix.engine.PhysicsEngine;
import com.draglantix.objects.Objects;

public abstract class Entity extends Objects {
	
	protected float speed;
	protected Vector2f currentSpeed = new Vector2f();
	private float lastDirX = -1;

	public Entity(int texture, Vector2f position, Vector2f rotation, Vector2f scale, Vector4f color, float speed, Polygon bounds) {
		super(texture, position, rotation, scale, color, bounds);
		this.speed = speed;
	}

	@Override
	public void tick() {
		// Add jump stuff and gravity to y
		move(currentSpeed);
		currentSpeed = new Vector2f(0, 0);
	}

	private void move(Vector2f dir) {
		if(bounds != null) {
			bounds.add(dir);
			checkForCollisions();
			position.set(bounds.getPosition());
		}else {
			PhysicsEngine.move(dir, this);
		}
	}

	protected void jump() {
		// set jump variables
	}

	protected void flip(float dirX) {
		float facing;
		if(dirX == 0) {
			facing = 0;
		} else {
			facing = dirX / Math.abs(dirX);
		}
		if(lastDirX != facing && facing != 0) {
			PhysicsEngine.flip(this);
			lastDirX = facing;
		}
	}
	
    private void checkForCollisions() {
		if(bounds.getIsMoveable()) {
			for(Objects entity : Engine.objects.get(Entity.class)) {
				if(!this.equals(entity) && entity.getBounds() != null) {
					Vector2f dir = Collisions.testCollision(bounds, entity.getBounds());
					if(dir != null)
						bounds.add(dir);
				}
			}
			for(Objects tile : PlayAssets.world.getCurrentTiles(position)) {
				if(tile.getBounds() != null) {
					Vector2f dir = Collisions.testCollision(bounds, tile.getBounds());
					if(dir != null)
						bounds.add(dir);
				}
			}

		}

	}

}
