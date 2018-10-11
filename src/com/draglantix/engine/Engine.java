package com.draglantix.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.draglantix.assets.Assets;
import com.draglantix.assets.PlayAssets;
import com.draglantix.entities.EntityRenderer;
import com.draglantix.entities.EntityShader;
import com.draglantix.font.GlyphRenderer;
import com.draglantix.font.GlyphShader;
import com.draglantix.foregrounds.FGRenderer;
import com.draglantix.foregrounds.FGShader;
import com.draglantix.guis.GuiRenderer;
import com.draglantix.guis.GuiShader;
import com.draglantix.objects.ObjectRenderer;
import com.draglantix.objects.Objects;
import com.draglantix.tiles.TileRenderer;
import com.draglantix.tiles.TileShader;

public class Engine {
	public static TileRenderer tileRenderer;
	public static EntityRenderer entityRenderer;
	public static FGRenderer fgRenderer;
	public static GuiRenderer guiRenderer;
	public static GlyphRenderer glyphRenderer;

	public static HashMap<Class<?>, List<Objects>> objects;
	public static HashMap<Class<?>, ObjectRenderer> renderers;

	public Engine(Assets assets) {
		objects = new HashMap<Class<?>, List<Objects>>();
		renderers = new HashMap<Class<?>, ObjectRenderer>();

		tileRenderer = new TileRenderer(new TileShader("tile"));
		entityRenderer = new EntityRenderer(new EntityShader("entity"));
		fgRenderer = new FGRenderer(new FGShader("fg"));
		guiRenderer = new GuiRenderer(new GuiShader("gui"));
		glyphRenderer = new GlyphRenderer(new GlyphShader("glyph"));
	}

	public static void renderState(List<Class<? extends Objects>> classes) {
		for(Class<? extends Objects> clazz : classes) {
			render(clazz);
		}
	}

	public static void tickState(List<Class<? extends Objects>> classes) {
		for(Class<? extends Objects> clazz : classes) {
			tick(clazz);
		}
	}

	public static <T extends Objects> void addObject(Class<T> clazz, T obj) {
		objects.get(clazz).add(obj);

	}

	private static <T extends Objects> void tick(Class<T> clazz) {
		if(clazz.getSimpleName().equals("Tile")) {
			for(Objects obj : PlayAssets.world.getCurrentTiles(PlayAssets.player.getPosition())) {
				obj.tick();
			}
		} else {
			for(Objects obj : objects.get(clazz)) {
				obj.tick();
			}
		}
	}

	private static <T extends Objects> void render(Class<T> clazz) {
		if(clazz.getSimpleName().equals("Tile")) {
			renderers.get(clazz).render(PlayAssets.world.getCurrentTiles(PlayAssets.player.getPosition()));
		} else {
			renderers.get(clazz).render(objects.get(clazz));
		}
	}

	public static <T extends Objects> void initObjects(Class<T> clazz) {
		objects.put(clazz, new ArrayList<Objects>());
	}

	public static <T extends Objects> void removeObject(Class<T> clazz, T obj) {
		objects.get(clazz).remove(obj);
	}

	public static void unloadAll() {
		for(Class<?> clazz : objects.keySet()) {
			objects.get(clazz).clear();
		}
	}

	public static void cleanUp() {
		for(Class<?> clazz : objects.keySet()) {
			renderers.get(clazz).cleanUp();
		}
	}

}