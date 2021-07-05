import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Exception;

import Flyable.AircraftFactory;
import Flyable.Aircraft.AircraftExeption;
import Weather.WeatherTower;

public class Simulation {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static class SimulationExeption extends Exception {
		public SimulationExeption() {
			super();
		}

		public SimulationExeption(String exeption) {
			super(exeption);
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println(ANSI_RED + "Please add a Scenario file name." + ANSI_RESET);
			return;
		}

		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;

			line = br.readLine();

			int iterations = Integer.parseInt(line);
			if (iterations < 0)
				throw new SimulationExeption("Iterations sholud be a positive number: " + iterations);

			WeatherTower tower = new WeatherTower();

			while ((line = br.readLine()) != null) {
				String aircraft[] = line.split(" ");

				try {
					if (aircraft.length != 5)
						throw new SimulationExeption(
								"Wrong simulation format (TYPE NAME LONGITUDE LATITUDE HEIGHT): " + line);
					if (Integer.parseInt(aircraft[2]) < 0 || Integer.parseInt(aircraft[3]) < 0)
						throw new SimulationExeption(
								"Coordinates are positive numbers: " + aircraft[2] + " " + aircraft[3]);
					if (Integer.parseInt(aircraft[4]) < 0 || Integer.parseInt(aircraft[4]) > 100)
						throw new SimulationExeption("The height is out of the 0-100 range: " + aircraft[4]);
					AircraftFactory.newAircraft(aircraft[0], aircraft[1], Integer.parseInt(aircraft[2]),
							Integer.parseInt(aircraft[3]), Integer.parseInt(aircraft[4])).registerTower(tower);
				} catch (SimulationExeption ex) {
					System.out.println(ANSI_RED + ex.getMessage() + ANSI_RESET);
				} catch (NumberFormatException ex) {
					System.out.println(
							ANSI_RED + "Should be a an integer number: " + line + ": " + ex.getMessage() + ANSI_RESET);
				} catch (AircraftExeption ex) {
					System.out.println(ANSI_RED + ex.getMessage() + ANSI_RESET);
				}
			}

			while (iterations-- > 0) {
				tower.changeWeather();
			}
		} catch (IOException ex) {
			System.out.println(ANSI_RED + ex.getMessage() + ANSI_RESET);
		} catch (NumberFormatException ex) {
			System.out.println(ANSI_RED + "Should be a an integer number: " + ex.getMessage() + ANSI_RESET);
		} catch (SimulationExeption ex) {
			System.out.println(ANSI_RED + ex.getMessage() + ANSI_RESET);
		}
	}
}
