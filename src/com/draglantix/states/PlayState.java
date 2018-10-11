package com.draglantix.states;

import java.util.List;

import org.lwjgl.glfw.GLFW;

import com.draglantix.assets.PlayAssets;
import com.draglantix.engine.Engine;
import com.draglantix.objects.Objects;
import com.draglantix.window.Window;
import com.draglantix.world.World;

public class PlayState extends GameState{
	
	public PlayState(GameStateManager gsm, List<Class<? extends Objects>> activeClasses) {
		super(gsm, activeClasses);
	}

	@Override
	protected void load(State state) {
		super.load(state);
		System.out.println("Play init");
		PlayAssets.world = new World();
	}
	
	@Override
	protected void tick() {
		Engine.tickState(activeClasses);
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
			gsm.setState(State.INTRO);
		}
	}
	
	protected void renderScene() {
		Engine.renderState(activeClasses);
	}
	
	protected void unload() {
		PlayAssets.world.unload();
	}

}
