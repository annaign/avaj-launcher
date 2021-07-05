package Flyable;

import Weather.Coordinates;

public abstract class Aircraft {
	private static long idCounter = 1;

	protected long id;
	protected String name;
	protected Coordinates coordinates;

	protected Aircraft(String name, Coordinates coordinates) {
		this.name = name;
		this.coordinates = coordinates;
		this.id = this.nextId();
	}

	private long nextId() {
		return Aircraft.idCounter++;
	}

	public String getName() {
		return this.name;
	}

	public long getId() {
		return this.id;
	}

	public static class AircraftExeption extends Exception {
		public AircraftExeption() {
			super();
		}

		public AircraftExeption(String exeption) {
			super(exeption);
		}

		public AircraftExeption(Throwable cause) {
			super(cause);
		}
	}
}
