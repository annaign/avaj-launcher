package Flyable;

import Flyable.Aircraft.AircraftExeption;
import Weather.Coordinates;

public final class AircraftFactory {
	public static Flyable newAircraft(String type, String name, int longtitude, int latitude, int height)
			throws AircraftExeption {
		Coordinates coordinates = new Coordinates(longtitude, latitude, height);

		if (type.equals("Helicopter")) {
			return new Helicopter(name, coordinates);
		} else if (type.equals("JetPlane")) {
			return new JetPlane(name, coordinates);
		} else if (type.equals("Baloon")) {
			return new Baloon(name, coordinates);
		} else {
			throw new AircraftExeption("Unknown Aircraft type");
		}
	}
}
