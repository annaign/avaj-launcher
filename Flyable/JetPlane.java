package Flyable;

import Weather.Coordinates;
import Weather.WeatherTower;
import Weather.WeatherTower.WeatherTowerException;

public class JetPlane extends Aircraft implements Flyable {
	private WeatherTower weatherTower;
	private String nameAircraft = "JetPlane";

	public JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() throws WeatherTowerException {
		String weather = this.weatherTower.getWeather(this.coordinates);
		String message = "landing.";

		if (weather.equals("SUN")) {
			message = "Let's enjoy the good weather and take some pics.";
			this.coordinates = new Coordinates(this.coordinates.getLongtitude(), this.coordinates.getLatitude() + 10,
					this.coordinates.getHeight() + 2 <= 100 ? this.coordinates.getHeight() + 4 : 100);
		} else if (weather.equals("RAIN")) {
			message = "It's raining. Better watch out for lightings.";
			this.coordinates = new Coordinates(this.coordinates.getLongtitude(), this.coordinates.getLatitude() + 5,
					this.coordinates.getHeight());
		} else if (weather.equals("FOG")) {
			message = "Nothing to see. This is fog...";
			this.coordinates = new Coordinates(this.coordinates.getLongtitude(), this.coordinates.getLatitude() + 1,
					this.coordinates.getHeight());
		} else if (weather.equals("SNOW")) {
			message = "OMG! Winter is coming!";
			this.coordinates = new Coordinates(this.coordinates.getLongtitude(), this.coordinates.getLatitude(),
					this.coordinates.getHeight() - 7);
		} else {
			throw new WeatherTowerException("Unknown weather from WeatherTower: " + weather);
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
