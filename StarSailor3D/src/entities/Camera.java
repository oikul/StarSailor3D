package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

public class Camera {

	private Vector4f position;
	private Matrix4f rotationMatrix;
	private float sensitivity = 0.01f;

	public Camera() {
		Mouse.setGrabbed(true);
		position = new Vector4f(1, 1, 1, 1);
		rotationMatrix = new Matrix4f();
		rotationMatrix.setIdentity();
	}

	public void move() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			Matrix4f.transform(rotationMatrix, position, position);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {

		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			Matrix4f.mul(rotateZ(-sensitivity), rotationMatrix, rotationMatrix);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			Matrix4f.mul(rotateZ(sensitivity), rotationMatrix, rotationMatrix);
		}
		if (Mouse.getX() - Display.getWidth() / 2 > 0) {
			Matrix4f.mul(rotateY(sensitivity), rotationMatrix, rotationMatrix);
		} else if (Mouse.getX() - Display.getWidth() / 2 < 0) {
			Matrix4f.mul(rotateY(-sensitivity), rotationMatrix, rotationMatrix);
		}
		if (Mouse.getY() - Display.getHeight() / 2 > 0) {
			Matrix4f.mul(rotateX(-sensitivity), rotationMatrix, rotationMatrix);
		} else if (Mouse.getY() - Display.getHeight() / 2 < 0) {
			Matrix4f.mul(rotateX(sensitivity), rotationMatrix, rotationMatrix);
		}
		Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
	}

	public Vector4f getPosition() {
		return position;
	}

	public Matrix4f getRotationMatrix() {
		return rotationMatrix;
	}

	public Matrix4f rotateX(float alpha) {
		Matrix4f rotate = new Matrix4f();
		rotate.m00 = 1;
		rotate.m01 = 0;
		rotate.m02 = 0;
		rotate.m03 = 0;
		rotate.m10 = 0;
		rotate.m11 = (float) Math.cos(alpha);
		rotate.m12 = (float) Math.sin(alpha);
		rotate.m13 = 0;
		rotate.m20 = 0;
		rotate.m21 = (float) -Math.sin(alpha);
		rotate.m22 = (float) Math.cos(alpha);
		rotate.m23 = 0;
		rotate.m30 = 0;
		rotate.m31 = 0;
		rotate.m32 = 0;
		rotate.m33 = 1;
		return rotate;
	}

	public Matrix4f rotateY(float beta) {
		Matrix4f rotate = new Matrix4f();
		rotate.m00 = (float) Math.cos(beta);
		rotate.m01 = 0;
		rotate.m02 = (float) -Math.sin(beta);
		rotate.m03 = 0;
		rotate.m10 = 0;
		rotate.m11 = 1;
		rotate.m12 = 0;
		rotate.m13 = 0;
		rotate.m20 = (float) Math.sin(beta);
		rotate.m21 = 0;
		rotate.m22 = (float) Math.cos(beta);
		rotate.m23 = 0;
		rotate.m30 = 0;
		rotate.m31 = 0;
		rotate.m32 = 0;
		rotate.m33 = 1;
		return rotate;
	}

	public Matrix4f rotateZ(float gamma) {
		Matrix4f rotate = new Matrix4f();
		rotate.m00 = (float) Math.cos(gamma);
		rotate.m01 = (float) Math.sin(gamma);
		rotate.m02 = 0;
		rotate.m03 = 0;
		rotate.m10 = (float) -Math.sin(gamma);
		rotate.m11 = (float) Math.cos(gamma);
		rotate.m12 = 0;
		rotate.m13 = 0;
		rotate.m20 = 0;
		rotate.m21 = 0;
		rotate.m22 = 1;
		rotate.m23 = 0;
		rotate.m30 = 0;
		rotate.m31 = 0;
		rotate.m32 = 0;
		rotate.m33 = 1;
		return rotate;
	}

}
