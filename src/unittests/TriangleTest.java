/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

import geometries.Plane;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * test class for {@link Triangle} 
 */
public class TriangleTest {

	/**
	 * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}
	 */
	@Test
	public void testFindIntersections() {
		Triangle trngl = new Triangle(new Point3D(1, 1, 1) , new Point3D(-2, -2, 1), new Point3D(-1, 1, -1));
		
		//___________________ Equivalence Partitions Tests__________________
		
		//TC01: inside triangle
		Ray r1 = new Ray(new Point3D(-2,2,2), new Vector(2,-2,-2));
		assertEquals("TC01: inside triangle", trngl.findIntersections(r1), List.of(new Point3D(-1d/3, 1d/3, 1d/3)));
		
		//TC02: Outside against edge
		Ray r2 = new Ray(new Point3D(-2, -2, 2) , new Vector(4,2,-2));
		assertNull("TC02: Outside against edge", trngl.findIntersections(r2));
		
		//TC03: Outside against vertex
		Ray r3 = new Ray(new Point3D(-4, -3, 2), new Vector(2,-1,-2));
		assertNull("TC03: Outside against vertex", trngl.findIntersections(r3));
		
		//___________________ Boundary Values Tests __________________
		
		//TC04: ray intersect the plane of the triangle on the edge of the triangle
		Ray r4 = new Ray(new Point3D(0, 0, -2), new Vector(-1.432240833943874, -0.296722501831622, 1.864481667887748));
		assertNull("TC04: ray intersect the plane on the edge of the triangle", trngl.findIntersections(r4));
		
		//TC05: ray intersect the plane of the triangle on the vertex of the triangle
		Ray r5 = new Ray(new Point3D(3,0,0), new Vector(-2,1,1));
		assertNull("TC04: ray intersect the plane of the triangle on the edge of the triangle", trngl.findIntersections(r5));
		
		//TC06: ray intersect the plane of the triangle on the edge's continuation of the triangle
		Ray r6 = new Ray(new Point3D(4, 0, 0), new Vector(0,4,1));
		assertNull("TC06: ray intersect the plane of the triangle on the edge's continuation of the triangle", trngl.findIntersections(r6));
		
	}

}
