package com.draglantix.states;

import java.util.List;

import org.lwjgl.glfw.GLFW;

import com.draglantix.assets.MenuAssets;
import com.draglantix.engine.Engine;
import com.draglantix.objects.Objects;
import com.draglantix.window.Window;

public class MenuState extends GameState{

	public MenuState(GameStateManager gsm, List<Class<? extends Objects>> activeClasses) {
		super(gsm, activeClasses);
	}

	@Override
	protected void load(State state) {
		super.load(state);
		System.out.println("Menu init");
	}
	
	@Override
	protected void tick() {
		Engine.tickState(activeClasses);
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
			gsm.setState(State.PLAY);
		}
	}
	
	protected void renderScene() {
		Engine.renderState(activeClasses);
	}
	
	protected void unload() {
		MenuAssets.unload();
	}

}
