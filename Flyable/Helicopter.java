package Flyable;

import Weather.Coordinates;
import Weather.WeatherTower;
import Weather.WeatherTower.WeatherTowerException;

public class Helicopter extends Aircraft implements Flyable {
	private WeatherTower weatherTower;
	private String nameAircraft = "Helicopter";

	public Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() throws WeatherTowerException {
		String weater = this.weatherTower.getWeather(this.coordinates);
		String message = "landing.";

		if (weater.equals("SUN")) {
			message = "This is hot.";
			this.coordinates = new Coordinates(this.coordinates.getLongtitude() + 10, this.coordinates.getLatitude(),
					this.coordinates.getHeight() + 2 <= 100 ? this.coordinates.getHeight() + 4 : 100);
		} else if (weater.equals("RAIN")) {
			message = "It's raining. Better watch out for lightings.";
			this.coordinates = new Coordinates(this.coordinates.getLongtitude() + 5, this.coordinates.getLatitude(),
					this.coordinates.getHeight());
		} else if (weater.equals("FOG")) {
			message = "Nothing to see. This is fog...";
			this.coordinates = new Coordinates(this.coordinates.getLongtitude() + 1, this.coordinates.getLatitude(),
					this.coordinates.getHeight());
		} else if (weater.equals("SNOW")) {
			message = "My rotor is going to freeze!";
			this.coordinates = new Coordinates(this.coordinates.getLongtitude(), this.coordinates.getLatitude(),
					this.coordinates.getHeight() - 12);
		} else {
			throw new WeatherTowerException("Unknown weather from WeatherTower");
		}

		System.out.println(nameAircraft + "#" + this.name + "(" + this.id + "): " + message);

		if (this.coordinates.getHeight() <= 0) {
			this.weatherTower.unregister(this);
			System.out.println(nameAircraft + "#" + this.name + "(" + this.id + "): longtitude = "
					+ this.coordinates.getLongtitude() + " latitude = " + this.coordinates.getLatitude() + " height = "
					+ this.coordinates.getHeight());
		}
	}

	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
	}

	@Override
	public String toString() {
		return nameAircraft;
	}
}
