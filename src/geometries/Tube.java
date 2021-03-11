package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * represents a tube in the space
 */
public class Tube implements Geometry{

	protected Ray axis;
	protected double radius;
	
	/**
	 * 
	 * @param axis the axis of the Tube
	 * @param radius the radius of the Tube
	 */
	public Tube(Ray axis, double radius) {
		this.axis = axis;
		this.radius = radius;
	}

	@Override
	public Vector getNormal(Point3D point) {
		return null;
	}
	
	@Override
	public String toString() {
		return "axis: " + axis.toString() + ", radius: " + radius;
	}

	/**
	 * 
	 * @return the axis of the Tube
	 */
	public Ray getAxis() {
		return axis;
	}

	/**
	 * 
	 * @return the Radius of the Tube
	 */
	public double getRadius() {
		return radius;
	}
}
