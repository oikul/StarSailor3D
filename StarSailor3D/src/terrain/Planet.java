package terrain;

import entities.Camera;
import entities.Light;
import renderEngine.Loader;

public class Planet extends PlanetaryBody {

	public static final float MAX_SIZE = 256, MAX_DISTANCE = 65536;
	private float rotation, rotationRate;

	public Planet(float size, float distance, float polar, float azimuthal, float rotationRate, int sattelites,
			long seed, Loader loader, String modelPath) {
		super(size, distance, polar, azimuthal, sattelites, seed, loader, modelPath);
		this.rotationRate = rotationRate;
	}

	@Override
	public void update() {
		increaseRotation(rotationRate);
	}

	@Override
	public void generateSurface() {
		generated = true;
	}

	@Override
	public void render(Light light, Camera camera) {

	}

	public void increaseRotation(float amount) {
		if (rotation < 2 * Math.PI) {
			rotation += amount;
		} else {
			rotation = 0.0f;
		}
	}

	public static int roundToTargetInt(double value, double target) {
		return (int) (Math.round(value / target) * target);
	}

}
