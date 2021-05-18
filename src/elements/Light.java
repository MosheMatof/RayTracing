/**
 * 
 */
package elements;

import primitives.Color;

/**
 * an abstract class of Light
 */
abstract class Light {
	protected Color intensity;

	/**
	 * Light constructor
	 * @param intensity the intensity of the light
	 */
	protected Light(Color intensity) {
		this.intensity = intensity;
	}

	/**
	 * get the intensity
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}	
}