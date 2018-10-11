package com.draglantix.tiles;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.draglantix.assets.PlayAssets;
import com.draglantix.objects.Objects;

public class Tile extends Objects {

	private TileData data;
	private TileBiomeData biomeData;

	public Tile(TileData data, Vector2f position, Vector2f rotation, Vector2f scale, Vector4f color,
			TileBiomeData biomeData) {
		super(data.getTexture().getTextureID(), position, rotation, scale, color,
				data.getBounding() ? PlayAssets.generateSquareBound(position, scale, false) : null);
		this.data = data;
		this.biomeData = biomeData;
	}

	@Override
	public void tick() {

	}

	public TileBiomeData getBiomeData() {
		return biomeData;
	}

	public TileData getData() {
		return data;
	}

	public void setData(TileData data) {
		this.data = data;
	}
	
	public void reset() {
		setTexture(data.getTexture().getTextureID());
	}
	
}
