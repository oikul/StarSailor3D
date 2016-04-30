package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrain.Terrain;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		MasterRenderer renderer = new MasterRenderer();
		Random random = new Random();
		RawModel model = OBJLoader.loadObjModel("planet_model_1", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("grass/grass_plains"));
		texture.setShineDamper(30);
		texture.setReflectivity(0);
		TexturedModel texturedModel = new TexturedModel(model, texture);
		Light light = new Light(new Vector3f(3000, 2000, 2000), new Vector3f(1f, 1f, 1f));
		Camera camera = new Camera();
		List<Entity> planets = new ArrayList<Entity>();
		for (int i = 0; i < 100; i++) {
			float x = random.nextFloat() * 100 - 50;
			float y = random.nextFloat() * 100 - 50;
			float z = random.nextFloat() * 100 - 50;
			planets.add(new Entity(texturedModel, new Vector3f(x, y, z), 0,0,0,1));
		}
		Terrain terrain = new Terrain(0, -1, loader, texture);
		Terrain terrain1 = new Terrain(1, -1, loader, texture);
		while (!Display.isCloseRequested()) {
			camera.move();
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain1);
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
