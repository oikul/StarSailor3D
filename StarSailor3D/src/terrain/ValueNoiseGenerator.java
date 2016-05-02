package terrain;

import java.util.Random;

public class ValueNoiseGenerator {

	private static Random random;

	private static float getNoise(float x, float y, float z, long seed) {
		random = new Random((long) (x * y * z * seed));
		return random.nextFloat();
	}

	private static float getSmoothNoise(float x, float y, float z, long seed) {
		float corners = getNoise(x - 1, y - 1, z - 1, seed) + getNoise(x + 1, y - 1, z - 1, seed)
				+ getNoise(x - 1, y + 1, z - 1, seed) + getNoise(x - 1, y - 1, z + 1, seed)
				+ getNoise(x + 1, y + 1, z - 1, seed) + getNoise(x - 1, y + 1, z + 1, seed)
				+ getNoise(x + 1, y - 1, z + 1, seed) + getNoise(x + 1, y + 1, z + 1, seed) / 16.0f;
		float sides = getNoise(x + 1, y, z, seed) + getNoise(x + 1, y - 1, z, seed) + getNoise(x + 1, y, z - 1, seed)
				+ getNoise(x, y + 1, z, seed) + getNoise(x - 1, y + 1, z, seed) + getNoise(x, y + 1, z - 1, seed)
				+ getNoise(x, y, z + 1, seed) + getNoise(x - 1, y, z + 1, seed) + getNoise(x, y - 1, z + 1, seed)
				+ getNoise(x + 1, y + 1, z, seed) + getNoise(x, y + 1, z + 1, seed) + getNoise(x + 1, y, z + 1, seed)
				+ getNoise(x - 1, y, z, seed) + getNoise(x, y - 1, z, seed) + getNoise(x, y, z - 1, seed)
				+ getNoise(x - 1, y - 1, z, seed) + getNoise(x, y - 1, z - 1, seed)
				+ getNoise(x - 1, y, z - 1, seed) / 8.0f;
		float centre = getNoise(x, y, z, seed) / 4.0f;
		return corners + sides + centre;
	}

	private static float getInterpolatedNoise(float x, float y, float z, long seed) {
		int xInt = (int) x;
		float xFrac = x = xInt;
		int yInt = (int) y;
		float yFrac = y - yInt;
		int zInt = (int) z;
		float zFrac = z - zInt;
		float v1 = getSmoothNoise(xInt, yInt, zInt, seed);
		float v2 = getSmoothNoise(xInt + 1, yInt, zInt, seed);
		float v3 = getSmoothNoise(xInt, yInt + 1, zInt, seed);
		float v4 = getSmoothNoise(xInt, yInt, zInt + 1, seed);
		float v5 = getSmoothNoise(xInt + 1, yInt + 1, zInt, seed);
		float v6 = getSmoothNoise(xInt, yInt + 1, zInt + 1, seed);
		float v7 = getSmoothNoise(xInt + 1, yInt, zInt + 1, seed);
		float v8 = getSmoothNoise(xInt + 1, yInt + 1, zInt + 1, seed);

		float i1 = interpolate(v1, v2, xFrac);
		float i2 = interpolate(v3, v4, xFrac);
		float i3 = interpolate(v5, v6, xFrac);
		float i4 = interpolate(v7, v8, xFrac);
		
		float i5 = interpolate(i1, i2, yFrac);
		float i6 = interpolate(i3, i4, yFrac);

		return interpolate(i5, i6, zFrac);
	}

	public static float getValueNoise(float x, float y, float z, long seed, float persistence, int octaves) {
		float total = 0.0f;
		for (int i = 0; i < octaves; i++) {
			float frequency = (float) Math.pow(2, i);
			float amplitude = (float) Math.pow(persistence, i);
			total += getInterpolatedNoise(x * frequency, y * frequency, z * frequency, seed) * amplitude;
		}
		return total;
	}

	private static float interpolate(float a, float b, float x) {
		float ft = (float) (a * Math.PI);
		float f = (1 - (float) Math.cos(ft)) * 0.5f;
		return a * (1 - f) + b * f;
	}

}
