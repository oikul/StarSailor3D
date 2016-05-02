package engineTester;

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
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		MasterRenderer renderer = new MasterRenderer();
		RawModel model = OBJLoader.loadObjModel("planet", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("grass/grass_plains"));
		texture.setShineDamper(100f);
		texture.setReflectivity(1f);
		TexturedModel texturedModel = new TexturedModel(model, texture);
		Light light = new Light(new Vector3f(0, 0, 2), new Vector3f(1f, 1f, 1f));
		Camera camera = new Camera();
		Entity planet = new Entity(texturedModel, new Vector3f(0, 0f, -5), 0, 0, 0, 1);
		while (!Display.isCloseRequested()) {
			camera.move();
			renderer.processEntity(planet);
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
