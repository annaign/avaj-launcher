package Weather;

public final class WeatherProvider {
	private static WeatherProvider weatherProvider;
	private static String weather[] = { "RAIN", "FOG", "SUN", "SNOW" };

	private WeatherProvider() {
	}

	public static WeatherProvider getWeatherProvider() {
		if (weatherProvider == null) {
			weatherProvider = new WeatherProvider();
		}

		return weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates) {
		int randPositive = coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongtitude();

		return weather[randPositive % weather.length];
	}
}
