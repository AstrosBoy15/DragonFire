package com.draglantix.states;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.draglantix.engine.Engine;
import com.draglantix.objects.Objects;
import com.draglantix.window.Window;

public abstract class GameState {

	protected GameStateManager gsm;
	protected List<Class<? extends Objects>> activeClasses;
	
	public GameState(GameStateManager gsm, List<Class<? extends Objects>> activeClasses) {
		this.gsm = gsm;
		this.activeClasses = activeClasses;
	}
	
	protected void load(State state) {
		gsm.getAssets().load(state);
	}
	
	protected void unload(State state) {
		System.out.println("unload");
		Engine.unloadAll();
		gsm.getAssets().unload(state);
	}
	
	protected abstract void tick();
	
	protected void render() {
		prepare();
		renderScene();
		finish(gsm.getWindow());
	}
	
	private void prepare() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(0, 0, 0, 1);
	}
	
	protected abstract void renderScene();
	
	protected abstract void unload();
	
	private void finish(Window window) {
		window.swapBuffers();
	}

	
}
