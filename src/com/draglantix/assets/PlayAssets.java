package com.draglantix.assets;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.draglantix.collsion.Polygon;
import com.draglantix.engine.Camera;
import com.draglantix.entities.Player;
import com.draglantix.models.SpriteSheet;
import com.draglantix.models.Texture;
import com.draglantix.world.World;

public class PlayAssets {

	public static Camera camera = null;
	public static Player player;
	
	public static World world;

	public static Texture playerTex, cloudTex, tileTex;

	private static SpriteSheet tiles;
	
	public static Texture tile1a, tile1b, tile1c, tile1d,
						tile2a, tile2b, tile2c, tile2d, tile2e, tile2f,
						tile3a, tile3b, tile3c, tile3d,
						tile4, tile5;
	
	public static void load() {
		playerTex = new Texture("sheep");
		cloudTex = new Texture("cloud");
		tileTex = new Texture("tile");

		tiles = new SpriteSheet("testTiles");
	
		tile1a = new Texture(tiles.crop(new Vector2f(0, 0), new Vector2f(32, 32)));
		tile1b = new Texture(tiles.crop(new Vector2f(32, 0), new Vector2f(32, 32)));
		tile1c = new Texture(tiles.crop(new Vector2f(64, 0), new Vector2f(32, 32)));
		tile1d = new Texture(tiles.crop(new Vector2f(96, 0), new Vector2f(32, 32)));
		
		tile2e = new Texture(tiles.crop(new Vector2f(0, 32), new Vector2f(32, 32)));
		tile2f = new Texture(tiles.crop(new Vector2f(32, 32), new Vector2f(32, 32)));
		tile2a = new Texture(tiles.crop(new Vector2f(64, 32), new Vector2f(32, 32)));
		tile2b = new Texture(tiles.crop(new Vector2f(96, 32), new Vector2f(32, 32)));
		tile2c = new Texture(tiles.crop(new Vector2f(0, 64), new Vector2f(32, 32)));
		tile2d = new Texture(tiles.crop(new Vector2f(32, 64), new Vector2f(32, 32)));
		
		tile3a = new Texture(tiles.crop(new Vector2f(64, 64), new Vector2f(32, 32)));
		tile3b = new Texture(tiles.crop(new Vector2f(96, 64), new Vector2f(32, 32)));
		tile3c = new Texture(tiles.crop(new Vector2f(0, 96), new Vector2f(32, 32)));
		tile3d = new Texture(tiles.crop(new Vector2f(32, 96), new Vector2f(32, 32)));
		
		tile4 = new Texture(tiles.crop(new Vector2f(64, 96), new Vector2f(32, 32)));
		tile5 = new Texture(tiles.crop(new Vector2f(96, 96), new Vector2f(32, 32)));
	
		player = new Player(playerTex.getTextureID(), new Vector2f(0, 0), new Vector2f(0, 0), new Vector2f(32, 32),
				new Vector4f(1, 0, 1, 1), 15f);
		camera = new Camera(new Vector2f(0, 0), 0, 0, 0.07f, player);
	}

	public static void unload() {
		playerTex = null;
	}

	public static Polygon generateSquareBound(Vector2f position, Vector2f scale, boolean moves) {
		return new Polygon(
				new float[] { position.x + scale.x, position.x + scale.x, position.x - scale.x, position.x - scale.x },
				new float[] { position.y + scale.y, position.y - scale.y, position.y - scale.y, position.y + scale.y },
				moves);
	}
}
