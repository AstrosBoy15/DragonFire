package com.draglantix.tiles;

import com.draglantix.assets.PlayAssets;

public class TileLib {
	
	public static TileData tile1a, tile1b, tile1c, tile1d,
	tile2a, tile2b, tile2c, tile2d, tile2e, tile2f,
	tile3a, tile3b, tile3c, tile3d,
	tile4, tile5;
	
	public static void loadTiles() {
		tile1a = new TileData(PlayAssets.tile1a, 0, false);
		tile1b = new TileData(PlayAssets.tile1b, 1, false);
		tile1c = new TileData(PlayAssets.tile1c, 2, false);
		tile1d = new TileData(PlayAssets.tile1d, 3, false);
		
		tile2a = new TileData(PlayAssets.tile2a, 4, false);
		tile2b = new TileData(PlayAssets.tile2b, 5, false);
		tile2c = new TileData(PlayAssets.tile2c, 6, false);
		tile2d = new TileData(PlayAssets.tile2d, 7, false);
		tile2e = new TileData(PlayAssets.tile2c, 8, false);
		tile2f = new TileData(PlayAssets.tile2d, 9, false);
		
		tile3a = new TileData(PlayAssets.tile3a, 10, false);
		tile3b = new TileData(PlayAssets.tile3b, 11, false);
		tile3c = new TileData(PlayAssets.tile3c, 12, false);
		tile3d = new TileData(PlayAssets.tile3d, 13, false);
		
		tile4 = new TileData(PlayAssets.tile4, 14, false);
		tile5 = new TileData(PlayAssets.tile5, 15, false);
	}

	
	
}
