package terrain;

import entities.Camera;
import entities.Light;
import renderEngine.Loader;

public class Star extends PlanetaryBody {

	public static final float MAX_SIZE = 1024.0f, MAX_DISTANCE = Float.MAX_VALUE;

	public Star(float size, float distance, float polar, float azimuthal, int sattelites, long seed, Loader loader,
			String modelPath) {
		super(size, distance, polar, azimuthal, sattelites, seed, loader, modelPath);
	}

	@Override
	public void update() {

	}

	@Override
	public void generateSurface(int stacks, int slices) {
		generated = true;
	}

	@Override
	public void render(Light light, Camera camera) {

	}

}
