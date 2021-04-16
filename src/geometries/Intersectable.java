/**
 * 
 */
package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * the interface provide the function findIntersections for any object in space, used to find intersections between objects and rays  
 */
public interface Intersectable {
	
	/**
	 * compute the intersection points between him self and the ray
	 * @param ray the intersect ray
	 * @return list of the intersection points
	 */
	List<Point3D> findIntersections(Ray ray);
}