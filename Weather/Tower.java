package Weather;

import Flyable.Flyable;
import Flyable.Helicopter;
import Flyable.JetPlane;
import Weather.WeatherTower.WeatherTowerException;
import Flyable.Baloon;

import java.util.ArrayList;

import Flyable.Aircraft;

public class Tower {
	private ArrayList<Flyable> observers = new ArrayList<Flyable>();

	private String getAircraftName(Aircraft aircraft) {
		String nameAircraft = "Unknown flayable";

		if (aircraft instanceof Helicopter) {
			nameAircraft = "Helicopter";
		} else if (aircraft instanceof JetPlane) {
			nameAircraft = "JetPlane";
		} else if (aircraft instanceof Baloon) {
			nameAircraft = "Baloon";
		}

		return nameAircraft;
	}

	public void register(Flyable flyable) {
		if (observers.contains(flyable)) {
			return;
		}

		this.observers.add(flyable);

		Aircraft aircraft = (Aircraft) flyable;
		System.out.println("\u001B[32m" + "Tower says: " + getAircraftName(aircraft) + "#" + aircraft.getName() + "("
				+ aircraft.getId() + ")" + " registered to weather tower." + "\u001B[0m");
	}

	public void unregister(Flyable flyable) {
		if (observers.contains(flyable)) {
			this.observers.remove(flyable);

			Aircraft aircraft = (Aircraft) flyable;
			System.out.println("\u001B[33m" + "Tower says: " + getAircraftName(aircraft) + "#" + aircraft.getName()
					+ "(" + aircraft.getId() + ")" + " unregistered to weather tower." + "\u001B[0m");
		}
	}

	protected void conditionsChanged() {
		for (int i = 0; i < observers.size(); i++) {
			try {
				observers.get(i).updateConditions();
			} catch (WeatherTowerException ex) {
				System.out.println("\u001B[31m" + ex + "\u001B[0m");
			}
		}
	}
}
