package renderer;

import java.util.List;

import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import static primitives.Util.*;
import scene.Scene;

/**
 * traces ray to find it's pixel color
 */
public class BasicRayTracer extends RayTracerBase {

	/**
	 * constructs a new BasicRayTracer with scene = 'scene'
	 * 
	 * @param scene the scene of the BasicRayTracer
	 */
	public BasicRayTracer(Scene scene) {
		super(scene);
	}

	@Override
	public Color traceRay(Ray ray) {
		if (scene.geometries == null) {
			return scene.background;
		}
		List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);
		if (points == null) {
			return scene.background;
		} else {
			return calcColor(ray.findClosestGeoPoint(points), ray);
		}
	}

	/**
	 * calculates the color for a given point
	 * 
	 * @param gPoint the point to find the color for
	 * @return the color of this point
	 */
	private Color calcColor(GeoPoint gPoint, Ray ray) {
		Color color = scene.ambientLight.getIntensity().add(gPoint.geometry.getEmission());
		color = color.add(calcLocalEffects(gPoint, ray));
		return color;
	}

	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir();
		Vector n = intersection.getNormal();
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		int nShininess = intersection.geometry.getMaterial().nShinines;
		double kd = intersection.geometry.getMaterial().kD, ks = intersection.geometry.getMaterial().kS;
		Color color = Color.BLACK;

		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = alignZero(n.dotProduct(l));

			if (nl * nv > 0) { // sign(nl) == sing(nv)
				Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd, l, n, lightIntensity),
						calcSpecular(ks, l, n, v, nShininess, lightIntensity));
			}
		}
		return color;
	}

	/**
	 * @param ks
	 * @param l
	 * @param n
	 * @param v
	 * @param nShininess
	 * @param lightIntensity
	 * @return
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r = n.scale(2 * n.dotProduct(l)).subtract(l).normalized();
		double scale = Math.pow(ks * Math.max(0, v.scale(-1).dotProduct(r)), nShininess);
		return lightIntensity.scale(scale);
	}

	/**
	 * @param kd
	 * @param l
	 * @param n
	 * @param lightIntensity
	 * @return
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double scale = Math.abs(l.dotProduct(n)) * kd;
		return lightIntensity.scale(scale);

	}

}
