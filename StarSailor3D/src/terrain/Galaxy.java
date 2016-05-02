package terrain;

import java.util.Random;

import entities.Camera;
import entities.Light;
import renderEngine.Loader;

public class Galaxy {

	private int numOfStars, maxPlanets, maxMoons, currentStar, currentPlanet, currentMoon;
	private PlanetaryBody stars[];
	private PlanetaryBody planets[][];
	private PlanetaryBody moons[][][];
	private Random random;
	private long seed;
	private Loader loader;

	public Galaxy(int numOfStars, int maxPlanets, int maxMoons, long seed, Loader loader) {
		stars = new Star[numOfStars];
		planets = new Planet[numOfStars][maxPlanets];
		moons = new Planet[numOfStars][maxPlanets][maxMoons];
		this.seed = seed;
		random = new Random(seed);
		this.loader = loader;
		generateStars();
	}

	private void generateStars() {
		for (int i = 0; i < numOfStars; i++) {
			stars[i] = new Star(Star.MAX_SIZE - (random.nextFloat() * Star.MAX_SIZE + 128.0f),
					Star.MAX_DISTANCE - (random.nextFloat() * Star.MAX_DISTANCE),
					random.nextFloat() * 2 * (float) Math.PI, random.nextFloat() * 2 * (float) Math.PI,
					random.nextInt(maxPlanets), seed, loader, "planet_models/planet");
		}
	}

	public void update() {
		for (int i = 0; i < numOfStars; i++) {
			for (int j = 0; j < maxPlanets; j++) {
				for (int k = 0; k < maxMoons; k++) {
					if (moons[i][j][k] != null && moons[i][j][k].isGenerated()) {
						moons[i][j][k].update();
					}
				}
				if (planets[i][j] != null && planets[i][j].isGenerated()) {
					planets[i][j].update();
				}
			}
			if (stars[i] != null && stars[i].isGenerated()) {
				stars[i].update();
			}
		}
	}

	public void render(Light light, Camera camera) {
		for (int i = 0; i < numOfStars; i++) {
			for (int j = 0; j < maxPlanets; j++) {
				for (int k = 0; k < maxMoons; k++) {
					if (moons[i][j][k] != null && moons[i][j][k].isGenerated()) {
						moons[i][j][k].render(light, camera);
					}
				}
				if (planets[i][j] != null && planets[i][j].isGenerated()) {
					planets[i][j].render(light, camera);
				}
			}
			if (stars[i] != null && stars[i].isGenerated()) {
				stars[i].render(light, camera);
			}
		}
	}

	public int getCurrentStar() {
		return currentStar;
	}

	public void setCurrentStar(int currentStar) {
		this.currentStar = currentStar;
	}

	public int getCurrentPlanet() {
		return currentPlanet;
	}

	public void setCurrentPlanet(int currentPlanet) {
		this.currentPlanet = currentPlanet;
	}

	public int getCurrentMoon() {
		return currentMoon;
	}

	public void setCurrentMoon(int currentMoon) {
		this.currentMoon = currentMoon;
	}

	public int getNumOfStars() {
		return numOfStars;
	}

	public int getMaxPlanets() {
		return maxPlanets;
	}

	public int getMaxMoons() {
		return maxMoons;
	}

}
