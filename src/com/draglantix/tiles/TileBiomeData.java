package com.draglantix.tiles;

import com.draglantix.assets.PlayAssets;
import com.draglantix.biomes.Biome;

public class TileBiomeData {
	
	private Biome biome;
	private float temperature, precipitation, height;
	
	public TileBiomeData(float temperature, float precipitation, float height) {
		this.temperature = temperature;
		this.precipitation = precipitation;
		this.height = height;
		this.biome = PlayAssets.world.biomes.getBiome(temperature, precipitation);
	}

	public Biome getBiome() {
		return biome;
	}

	public float getTemperature() {
		return temperature;
	}

	public float getPrecipitation() {
		return precipitation;
	}

	public float getHeight() {
		return height;
	}
	
	
	
}
