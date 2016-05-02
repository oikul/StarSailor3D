package terrain;

import org.lwjgl.util.vector.Vector3f;

public class PolarVertex {

	private Vector3f centre;
	private float distance, polar, azimuthal;

	public PolarVertex(Vector3f centre, float distance, float polar, float azimuthal) {
		this.centre = centre;
		this.distance = distance;
		this.polar = polar;
		this.azimuthal = azimuthal;
	}
	
	public Vector3f getCentre(){
		return centre;
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

}
