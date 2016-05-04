package terrain;

public class Chunk {
	
	private float[][] values;

	public Chunk(float startPolar, float startAzimuthal, int size, long seed) {
		values = new float[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				values[i][j] = ValueNoiseGenerator.getValueNoise(startPolar, startAzimuthal, size, seed, 0.5f, 4);
			}
		}
	}

}
