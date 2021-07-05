package Flyable;

import Weather.Coordinates;
import Weather.WeatherTower;
import Weather.WeatherTower.WeatherTowerException;;

public class Baloon extends Aircraft implements Flyable {
	private WeatherTower weatherTower;
	private String nameAircraft = "Baloon";

	public Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() throws WeatherTowerException {
		String weater = this.weatherTower.getWeather(this.coordinates);
		String message = "landing.";

		if (weater.equals("SUN")) {
			message = "Let's enjoy the good weather and take some pics.";
			this.coordinates = new Coordinates(this.coordinates.getLongtitude() + 2, this.coordinates.getLatitude(),
					this.coordinates.getHeight() + 4 <= 100 ? this.coordinates.getHeight() + 4 : 100);
		} else if (weater.equals("RAIN")) {
			message = "Damn you rain! You messed up my baloon.";
			this.coordinates = new Coordinates(this.coordinates.getLongtitude(), this.coordinates.getLatitude(),
					this.coordinates.getHeight() - 5);
		} else if (weater.equals("FOG")) {
			message = "Nothing to see. This is fog...";
			this.coordinates = new Coordinates(this.coordinates.getLongtitude(), this.coordinates.getLatitude(),
					this.coordinates.getHeight() - 3);
		} else if (weater.equals("SNOW")) {
			message = "It's snowing. We're gonna crash.";
			this.coordinates = new Coordinates(this.coordinates.getLongtitude(), this.coordinates.getLatitude(),
					this.coordinates.getHeight() - 15);
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
