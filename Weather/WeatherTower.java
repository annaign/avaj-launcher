package Weather;

public class WeatherTower extends Tower {
	public String getWeather(Coordinates coordinates) {
		return WeatherProvider.getWeatherProvider().getCurrentWeather(coordinates);
	}

	public void changeWeather() {
		this.conditionsChanged();
	}

	public static class WeatherTowerException extends Exception {
		public WeatherTowerException() {
			super();
		}

		public WeatherTowerException(String exeption) {
			super(exeption);
		}

		public WeatherTowerException(Throwable cause) {
			super(cause);
		}
	}
}
