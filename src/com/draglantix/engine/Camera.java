package com.draglantix.engine;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.entities.Player;
import com.draglantix.util.Maths;
import com.draglantix.window.Window;

public class Camera {

	private Vector2f position;
	private float roll, lerp, zoom;
	
	private Vector2f shake = new Vector2f(0, 0), drift = new Vector2f(0, 0);
	private float shakeAmp = 1, shakeSpeed, driftIndex = 0;
	private Vector2f[] targets = null;
	private int currentTarget;
	
	private Player player;

	public Camera(Vector2f position, float roll, float zoom, float lerp, Player player) {
		this.position = position;
		this.roll = roll;
		this.zoom = zoom;
		this.lerp = lerp;
		this.player = player;
	}

	public void update() {
		move();
	}

	public void move() {
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_R)) {
			roll += 1f;
		}
		
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_Q) && zoom < .25f) {
			zoom += 0.01f;
		}
		
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_E) && zoom > -2) {
			zoom -= 0.01f;
		}
		
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_F)) {
			requestShake(5, 100, .8f);
		}
		
		drift();
		updateShake();
		
		position.lerp(player.getPosition(), lerp);
	}
	
	private void drift() {
		if(driftIndex < 360) {
			driftIndex += 0.02f;
		}		
		if(driftIndex >= 360) {
			driftIndex = 0;
		}
		drift.x = (float) Math.sin(driftIndex);
		drift.y = (float) Math.cos(driftIndex / 2);
		position.add(drift);
	}
	
	public void requestShake(int length, float amp, float speed) {
		shakeAmp = amp;
		shakeSpeed = speed;
		targets = new Vector2f[length];
		for(int i = 0; i < length; i ++) {
			targets[i] = new Vector2f(Maths.generateFloat(amp), Maths.generateFloat(amp));
		}
	}
	
	private void updateShake() {
		if(targets != null) {
			if(shake.distance(targets[currentTarget]) <= .1) {
				if(currentTarget < targets.length - 1) {
					currentTarget ++;
				}else {
					currentTarget = 0;
					shake.x = 0;
					shake.y = 0;
					targets = null;
				}
			}else {
				shake.lerp(targets[currentTarget], shakeSpeed);
			}
			position.add(shake);	
		}
	}

	public Matrix4f createViewMatrix() {
		Matrix4f view = new Matrix4f();
		view.scale((float) Math.exp(zoom + (shake.y / (shakeAmp*2))));
		view.rotate((float) (Math.toRadians(roll + (shake.x / 2))), 0, 0, 1);
		view.translate(-position.x, -position.y, 0);
		return view;
	}

	public Vector2f getPosition() {
		return position;
	}
	
	public float getZoom() {
		return (float) Math.exp(zoom);
	}

}