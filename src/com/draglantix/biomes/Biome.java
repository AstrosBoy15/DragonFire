package com.draglantix.biomes;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Biome {

	private Vector2f temperature, precipitation, height;
	private int id;
	private String name;
	private Vector3f color;
	
	public Biome(Vector2f temperature, Vector2f precipitation, Vector2f height, int id, Vector3f color, String name) {
		this.temperature = temperature;
		this.precipitation = precipitation;
		this.height = height;
		this.id = id;
		this.color = color;
		this.name = name;
	}

	public Vector2f getTemperature() {
		return temperature;
	}

	public Vector2f getPrecipitation() {
		return precipitation;
	}

	public Vector2f getHeight() {
		return height;
	}

	public int getId() {
		return id;
	}
	
	public Vector3f getColor() {
		return color;
	}

	public String getName() {
		return name;
	}
	
	
	
}
