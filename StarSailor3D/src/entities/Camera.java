package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch, yaw, roll;
	private int pastX = Display.getWidth()/2, pastY = Display.getHeight()/2;
	
	public void move(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.x += 0.01 * Math.sin(Math.toRadians(yaw));
			position.z -= 0.01 * Math.cos(Math.toRadians(yaw));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			roll -= 0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.x -= 0.01 * Math.sin(Math.toRadians(yaw));
			position.z += 0.01 * Math.cos(Math.toRadians(yaw));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			roll += 0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			position.y += 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			position.y -= 0.02f;
		}
		if(Mouse.getX() - pastX > 0){
			yaw += 1;
		}else if(Mouse.getX() - pastX < 0){
			yaw -= 1;
		}
		if(Mouse.getY() - pastY > 0){
			pitch -= 0.8;
		}else if(Mouse.getY() - pastY < 0){
			pitch += 0.8;
		}
		Mouse.setCursorPosition(pastX, pastY);
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

}
