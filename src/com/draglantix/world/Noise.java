package com.draglantix.world;
import java.util.Random;

public class Noise {

	private float amplitude;
	private int octaves;
	private float roughness;
	
	private Random rand;
	private int seed;
	private static final int CONST_1 = 105768;
	private static final int CONST_2 = 948102;

	public Noise(float amp, int oct, float rough, int seed) {
		this.seed = seed;
		this.amplitude = amp;
		this.octaves = oct;
		this.roughness = rough;
		this.rand = new Random();
		rand.setSeed(seed);
	}
	
	 public float generateHeight(int x, int y) {
		
		x += 20000000/2;
		y += 20000000/2;
		
	    float total = 0;
	    float d = (float) Math.pow(2, octaves-1);
	    for(int i=0;i<octaves;i++){
	        float freq = (float) (Math.pow(2, i) / d);
	        float amp = (float) Math.pow(roughness, i) * amplitude;
	        total += getInterpolatedNoise(x*freq, y*freq) * amp;
	    }
	    return total;
	}
	
	private float getInterpolatedNoise(float x, float y) {
		int intX = (int) x;
		int intY = (int) y;
		float fracX = x - intX;
		float fracY = y - intY;
		
		float v1 = getSmoothNoise(intX, intY);
		float v2 = getSmoothNoise(intX + 1, intY);
		float v3 = getSmoothNoise(intX, intY + 1);
		float v4 = getSmoothNoise(intX + 1, intY + 1);
		float i1 = interpolate(v1, v2, fracX);
		float i2 = interpolate(v3, v4, fracX);
		return interpolate(i1, i2, fracY);	
	}
	
	private float interpolate(float a, float b, float blend) {
		double theta = blend * Math.PI;
		float f = (float) ((1f - Math.cos(theta)) * 0.5f);
		return a * (1f - f) + b * f;
	}
	
	private float getSmoothNoise(int x, int y) {
		float corners = (getNoise(x - 1, y - 1) + getNoise(x + 1, y - 1)
		+ getNoise(x - 1, y + 1)+ getNoise(x + 1, y + 1)) / 16f;
		
		float sides = (getNoise(x - 1, y) + getNoise(x + 1, y)
		+ getNoise(x, y - 1) + getNoise(x, y + 1)) / 8f;
		
		float center = getNoise(x, y) / 4f;
		return corners + sides + center;
	}
	
	private float getNoise(int x, int y) {
		rand.setSeed(x * CONST_1 + y * CONST_2 + seed);
		return rand.nextFloat() * 2f - 1f;
	}
	
}