package Flyable;

import Weather.WeatherTower;
import Weather.WeatherTower.WeatherTowerException;;

public interface Flyable {
	void updateConditions() throws WeatherTowerException;

	void registerTower(WeatherTower weatherTower);
}
