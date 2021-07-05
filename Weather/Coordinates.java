package Weather;

public class Coordinates {
	private int longtitude;
	private int latitude;
	private int height;

	public Coordinates(int longtitude, int latitude, int height) {
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.height = height;
	}

	public int getLongtitude() {
		return longtitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public int getHeight() {
		return height;
	}
}
