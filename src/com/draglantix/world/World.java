package com.draglantix.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector4f;

import com.draglantix.assets.PlayAssets;
import com.draglantix.biomes.BiomeLib;
import com.draglantix.engine.Engine;
import com.draglantix.entities.Entity;
import com.draglantix.objects.Objects;
import com.draglantix.tiles.Tile;
import com.draglantix.tiles.TileLib;
import com.draglantix.tiles.TileMap;
import com.draglantix.util.Maths;

public class World {

	public static final int TILE_SIZE = 16;
	public static final int CHUNK_SIZE = 24;
	public static final int SCALE = 2;
	public static final int RENDER_DISTANCE = 1;

	public HashMap<Vector2i, Chunk> chunks2;

	public Chunk current;

	public BiomeLib biomes = new BiomeLib();
	public Noise noise;

	public final int SEED = Maths.rand().nextInt(100000);

	public World() {
		chunks2 = new HashMap<Vector2i, Chunk>();
		TileLib.loadTiles();
		noise = new Noise(3, 5, 0.1f, SEED);
		generate();
	}

	public void generate() {
		addObjects();
	}

	public void updateChunks(Vector2f pos) {
		int x = (int) (pos.x / TILE_SIZE / SCALE) % CHUNK_SIZE;
		int y = (int) (pos.y / TILE_SIZE / SCALE) % CHUNK_SIZE;
		Vector2i chunkPos = new Vector2i();
		if(pos.x > 0) {
			chunkPos.x = (int) (pos.x / TILE_SIZE / SCALE - x + CHUNK_SIZE / 2) / CHUNK_SIZE;
		} else {
			chunkPos.x = (int) (pos.x / TILE_SIZE / SCALE - x - CHUNK_SIZE / 2) / CHUNK_SIZE;
		}
		if(pos.y > 0) {
			chunkPos.y = (int) (pos.y / TILE_SIZE / SCALE - y + CHUNK_SIZE / 2) / CHUNK_SIZE;
		} else {
			chunkPos.y = (int) (pos.y / TILE_SIZE / SCALE - y - CHUNK_SIZE / 2) / CHUNK_SIZE;
		}

		if(chunks2.get(chunkPos) == null) {
			setChunk(chunkPos, new Chunk(chunkPos, this));
		}
		
		current = getChunk(chunkPos);
	}
	
	public void updateTiles(TileMap map) {
		int up, down, left, right; 
		Tile center;
		
		List<Tile> change0 = new ArrayList<Tile>();
		Map<Tile, Integer> change1 = new HashMap<Tile, Integer>();
		Map<Tile, Integer> change2 = new HashMap<Tile, Integer>();
		Map<Tile, Integer> change3 = new HashMap<Tile, Integer>();
		List<Tile> change4 = new ArrayList<Tile>();
	
		for(int x = -CHUNK_SIZE / 2; x < CHUNK_SIZE / 2; x++) {
			for(int y = -CHUNK_SIZE / 2; y < CHUNK_SIZE / 2; y++) {
				
				int dir = 0;
				
				center = map.getTile(x, y);
				int cID = center.getData().getID();
				
				try {
				
					up = map.getTile(x, y + 1).getData().getID();
					down =  map.getTile(x, y - 1).getData().getID();
					left =  map.getTile(x - 1, y).getData().getID();
					right =  map.getTile(x + 1, y).getData().getID();
				
					int[] sim = new int[4];
				
					sim[0] = left;
					sim[1] = up;
					sim[2] = right;
					sim[3] = down;
				
					int simCount = 0;
					
					List<Integer> diff = new ArrayList<Integer>();
					
					for(int i = 0; i < sim.length; i++) {
						int t = sim[i];
						
						if(t == cID){
							simCount ++;
						}else {
							diff.add(i);
						}
					}
							
					if(simCount == 0) {
						change0.add(center);
					}else if(simCount == 1) {
						if(!diff.contains(0)) {
							dir = 0;
						}else if(!diff.contains(1)) {
							dir = 1;
						}else if(!diff.contains(2)) {
							dir = 2;
						}else {
							dir = 3;
						}
						change1.put(center, dir);
					}else if(simCount == 2) {
						if(!diff.contains(0) && !diff.contains(1)) {
							dir = 0;
						}else if(!diff.contains(1) && !diff.contains(2)) {
							dir = 1;
						}else if(!diff.contains(2) && !diff.contains(3)) {
							dir = 2;
						}else if(!diff.contains(3) && !diff.contains(0)) {
							dir = 3;
						}else if(!diff.contains(0) && !diff.contains(3)) {
							dir = 4;
						}else {
							dir = 5;
						}
						change2.put(center, dir);
					}else if(simCount == 3) {
						dir = diff.get(0);
						change3.put(center, dir);
					}else {
						change4.add(center);
					}
				}catch(Exception e) {
					
				}
			}
		}
		
		for(Tile t: change0) {
			t.setData(TileLib.tile4);
			t.reset();
		}
		for(Tile t: change1.keySet()) {
			if(change1.get(t) == 0) {
				t.setData(TileLib.tile3c);
			}else if(change1.get(t) == 1) {
				t.setData(TileLib.tile3d);
			}else if(change1.get(t) == 2) {
				t.setData(TileLib.tile3a);
			}else {
				t.setData(TileLib.tile3b);
			}
			t.reset();
		}
		for(Tile t: change2.keySet()) {
			if(change2.get(t) == 0) {
				t.setData(TileLib.tile2c);
			}else if(change2.get(t) == 1) {
				t.setData(TileLib.tile2d);
			}else if(change2.get(t) == 2) {
				t.setData(TileLib.tile2a);
			}else if(change2.get(t) == 3) {
				t.setData(TileLib.tile2b);
			}else if(change2.get(t) == 4) {
				t.setData(TileLib.tile2f);
			}else {
				t.setData(TileLib.tile2e);
			}
			t.reset();
		}
		for(Tile t: change3.keySet()) {
			if(change3.get(t) == 0) {
				t.setData(TileLib.tile1a);
			}else if(change3.get(t) == 1) {
				t.setData(TileLib.tile1b);
			}else if(change3.get(t) == 2) {
				t.setData(TileLib.tile1c);
			}else {
				t.setData(TileLib.tile1d);
			}
			t.reset();
		}
		for(Tile t: change4) {
			t.setData(TileLib.tile5);
			t.reset();
		}
		
	}

	public void fill(TileMap map, Vector2f offset) {
		for(int x = -CHUNK_SIZE / 2; x < CHUNK_SIZE / 2; x++) {
			for(int y = -CHUNK_SIZE / 2; y < CHUNK_SIZE / 2; y++) {
				int fx = (int) (x + ((CHUNK_SIZE / 2) * offset.x));
				int fy = (int) (y + ((CHUNK_SIZE / 2) * offset.y));
				map.setTile(createTile(fx, fy), x, y);
			}
		}
		updateTiles(map);
	}

	public Tile createTile(int x, int y) {

		Vector2f scale = new Vector2f(TILE_SIZE * SCALE, TILE_SIZE * SCALE);

		Vector2f pos = new Vector2f((x * scale.x + scale.x / 2) * 2, (y * scale.y + scale.y / 2) * 2);

		Tile tile = new Tile(TileLib.tile5, pos, new Vector2f(0, 0), scale,
				new Vector4f(new Vector3f(noise.generateHeight(x, y)), 1), null);

		if(tile.getColor().x <= 0.09f) {
			tile.setColor(new Vector4f(0, .5f, 1, 1));
		} else if(tile.getColor().x > 0.09f && tile.getColor().x < 0.3) {
			tile.setColor(new Vector4f(0, 6, 14, 1).mul(tile.getColor()));
		} else if(tile.getColor().x >= 0.3 && tile.getColor().x < 0.7) {
			tile.setData(TileLib.tile1a); //sim diff tile gen
			tile.setColor(new Vector4f(3f, 3f, 2f, 1).mul(tile.getColor()));
			tile.reset();
		} else if(tile.getColor().x >= 0.7 && tile.getColor().x < 3) {
			tile.setColor(new Vector4f(0, .5f, .2f, 1).mul(tile.getColor()));
		} else {
			tile.setColor(new Vector4f(1, 1, 1, 1).mul(tile.getColor()));
		}

		return tile;

	}

	public List<Objects> getCurrentTiles(Vector2f pos) {
		List<Objects> tiles = new ArrayList<Objects>();

		updateChunks(pos);

		Vector2i currentPos = current.getPosition();

		int numTiles = CHUNK_SIZE - 1;

		for(int x = -RENDER_DISTANCE; x <= RENDER_DISTANCE; x++) {
			for(int y = -RENDER_DISTANCE; y <= RENDER_DISTANCE; y++) {

				if(getChunk(new Vector2i(currentPos.x + x, currentPos.y + y)) == null) {
					setChunk(new Vector2i(currentPos.x + x, currentPos.y + y),
							new Chunk(new Vector2i(currentPos.x + x, currentPos.y + y), this));
				}
				TileMap map = getChunk(new Vector2i(currentPos.x + x, currentPos.y + y)).getMap();
				for(int i = -numTiles / 2; i < numTiles / 2; i++) {
					for(int j = -numTiles / 2; j < numTiles / 2; j++) {
						tiles.add(map.getTile(i, j));
					}
				}
			}
		}
		
		return tiles;
	}

	public Chunk getChunk(Vector2i pos) {
		return chunks2.get(pos);
	}

	public void setChunk(Vector2i pos, Chunk chunk) {
		chunks2.put(pos, chunk);
	}

	public void addObjects() {
		Engine.addObject(Entity.class, PlayAssets.player);
	}

	public void unload() {

	}
}
