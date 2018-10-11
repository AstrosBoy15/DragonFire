package com.draglantix.biomes;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class BiomeLib {

	private Biome none, plains, desert, taiga, cold;
	private List<Biome> biomes;

	public BiomeLib() {
		none = new Biome(new Vector2f(-100, -99), new Vector2f(-100, -99), new Vector2f(-100, -99), 0,
				new Vector3f(0, 0, 0), "None");
		plains = new Biome(new Vector2f(0, 25), new Vector2f(0, 10), new Vector2f(0, 10), 1,
				new Vector3f(71 / 256f, 172 / 256f, 48 / 256f), "Plains");
		desert = new Biome(new Vector2f(25, 40), new Vector2f(0, 10), new Vector2f(0, 10), 2,
				new Vector3f(200 / 256f, 205 / 256f, 38 / 256f), "Desert");
		taiga = new Biome(new Vector2f(0, 25), new Vector2f(10, 20), new Vector2f(0, 10), 3,
				new Vector3f(41 / 256f, 125 / 256f, 22 / 256f), "Taiga");
		cold = new Biome(new Vector2f(25, 40), new Vector2f(10, 20), new Vector2f(0, 10), 4,
				new Vector3f(64 / 256f, 177 / 256f, 149 / 256f), "Cold"); // Shhhhh. Cold biomes definitely reach 100
																			// degrees fahrenheit.
		biomes = new ArrayList<>();
		biomes.add(none.getId(), none);
		biomes.add(plains.getId(), plains);
		biomes.add(desert.getId(), desert);
		biomes.add(plains.getId(), taiga);
		biomes.add(desert.getId(), cold);
	}

	public Biome getBiome(int id) {
		return biomes.get(id);
	}

	public Biome getBiome(float temp, float precip) {
		for(Biome biome : biomes) {
			if(biome.getTemperature().x <= temp && biome.getTemperature().y >= temp) {
				if(biome.getPrecipitation().x <= precip && biome.getPrecipitation().y >= precip) {
					return biome;
				}
			}
		}
		return none;
	}

}
