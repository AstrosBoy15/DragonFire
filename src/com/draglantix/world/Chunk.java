package com.draglantix.world;

import org.joml.Vector2f;
import org.joml.Vector2i;

import com.draglantix.tiles.TileMap;

public class Chunk {

	private TileMap map;
	private Vector2i pos;
	
	public Chunk(Vector2i pos, World world) {
		this.pos = pos;
		map = new TileMap(World.CHUNK_SIZE, World.CHUNK_SIZE);
		genTiles(world);
	}
	
	public TileMap getMap() {
		return map;
	}
	
	public void genTiles(World world) {
		world.fill(map, new Vector2f(pos));
	}
	
	public Vector2i getPosition() {
		return pos;
	}
	
}
