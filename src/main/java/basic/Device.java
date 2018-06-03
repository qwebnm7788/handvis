package basic;

public class Device {
	private int deviceNumber;
	private int fingerNumber;
	private String name;
	private String background_image;
	private int state;

	//default Constructor
	public Device() {
	}
	
	public Device(int deviceNumber, int fingerNumber, String name, String background_image, int state) {
		super();
		this.deviceNumber = deviceNumber;
		this.fingerNumber = fingerNumber;
		this.name = name;
		this.background_image = background_image;
		this.state = state;
	}

	public int getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(int deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public int getFingerNumber() {
		return fingerNumber;
	}

	public void setFingerNumber(int fingerNumber) {
		this.fingerNumber = fingerNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBackground_image() {
		return background_image;
	}

	public void setBackground_image(String background_image) {
		this.background_image = background_image;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return deviceNumber + " " + fingerNumber + " " + background_image + " " + state;
	}
}
