package terrain;

import java.util.Random;

import entities.Camera;
import entities.Light;
import models.RawModel;
import renderEngine.Loader;
import renderEngine.OBJLoader;

public abstract class PlanetaryBody {

	protected RawModel model;
	protected float size, distance, polar, azimuthal;
	protected int sattelites;
	protected Random random;
	protected long seed;
	protected boolean generated = false;

	public PlanetaryBody(float size, float distance, float polar, float azimuthal, int sattelites, long seed, Loader loader, String modelPath) {
		this.size = size;
		this.distance = distance;
		this.polar = polar;
		this.azimuthal = azimuthal;
		this.sattelites = sattelites;
		this.seed = seed;
		model = OBJLoader.loadObjModel(modelPath, loader);
		random = new Random((long) (size + distance + polar + azimuthal + sattelites + seed));
	}

	public abstract void update();

	public abstract void generateSurface(int stacks, int slices);

	public abstract void render(Light light, Camera camera);

	public float getSize() {
		return size;
	}

	public float getDistance() {
		return distance;
	}

	public float getPolar() {
		return polar;
	}

	public float getAzimuthal() {
		return azimuthal;
	}

	public int getSattelites() {
		return sattelites;
	}

	public long getSeed() {
		return seed;
	}

	public boolean isGenerated() {
		return generated;
	}

}
