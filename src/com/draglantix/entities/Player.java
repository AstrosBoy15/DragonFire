package com.draglantix.entities;

import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.assets.PlayAssets;
import com.draglantix.window.Window;
import com.draglantix.world.World;

public class Player extends Entity {

	private Vector2f chunk = new Vector2f(0, 0);
	
	public Player(int texture, Vector2f position, Vector2f rotation, Vector2f scale, Vector4f color, float speed) {
		super(texture, position, rotation, scale, new Vector4f(1, 1, 1, 1), speed,
				PlayAssets.generateSquareBound(position, scale, true));
	}

	@Override
	public void tick() {
		super.tick();
		movePlayer();
		handleChunks();
	}

	private void movePlayer() {

		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_A)) {
			currentSpeed.x -= speed;
		}
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_W)) {
			currentSpeed.y += speed;
		}
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_S)) {
			currentSpeed.y -= speed;
		}
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_D)) {
			currentSpeed.x += speed;
		}

		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_A) && !Window.getInput().isKeyDown(GLFW.GLFW_KEY_D)
				|| !Window.getInput().isKeyDown(GLFW.GLFW_KEY_A) && Window.getInput().isKeyDown(GLFW.GLFW_KEY_D)) {
			flip(currentSpeed.x);
		}
	}
	
	private void handleChunks() {
		
		//TODO need to account for chunk location here and in the actual bounds method
		
		//Set the chunk location
		
		if(bounds.getPosition().x + bounds.getWidth() / 2 >= (World.CHUNK_SIZE * World.TILE_SIZE) * World.SCALE) {
			
		}else if(bounds.getPosition().x - bounds.getWidth() / 2 <= -(World.CHUNK_SIZE * World.TILE_SIZE) * World.SCALE) {
			
		}else {
			
		}
		
		if(bounds.getPosition().y + bounds.getHeight() / 2 >= (World.CHUNK_SIZE * World.TILE_SIZE) * World.SCALE) {
			
		}else if(bounds.getPosition().y - bounds.getHeight() / 2 <= -(World.CHUNK_SIZE * World.TILE_SIZE)){
		
		}else {
			
		}
	}
	
	public Vector2f getChunk() {
		return chunk;
	}

}
