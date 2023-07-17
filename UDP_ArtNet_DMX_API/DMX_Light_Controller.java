package UDP_ArtNet_DMX_API;

import java.util.Random;

public class DMX_Light_Controller {

	private static final String IP_ADDRESS = "192.168.10.20";
	private static final int PORT = 6454;
	private static final Random RANDOM = new Random();

	private static UDPsender udpSender;
	private static ArtNet_AmaranLED_Controller amaranLED_Controller;

	public static void main(String[] args) throws InterruptedException {
		udpSender = new UDPsender(IP_ADDRESS, PORT);
		System.out.println("UDP sender created with IP: " + IP_ADDRESS + ", Port: " + PORT);

		amaranLED_Controller = new ArtNet_AmaranLED_Controller();
		System.out.println("Amaran LED Controller created.");

		// Start by turning off the light
		amaranLED_Controller.setTheLightOff();
		sendOrder(); 
		Thread.sleep(2000);
		System.out.println("Turned off the light for 2000  milliseconds");
		System.out.println("======================================"); 

		amaranLED_Controller.turnOnRedLight();
		sendOrder();
		System.out.println("Turned on red light"); 
		int delay = getRandomSleepDelay();
		System.out.println("Sleep delay: " + delay + " milliseconds");
		System.out.println("======================================"); 
		Thread.sleep(delay);

		amaranLED_Controller.turnOnBlinkingGreenLight();
		sendOrder();
		System.out.println("Turned on blinking green light"); 
		delay = getRandomSleepDelay();
		System.out.println("Sleep delay: " + delay + " milliseconds");
		System.out.println("======================================");  
		Thread.sleep(delay);

		amaranLED_Controller.setTheLightOff();
		sendOrder();
		System.out.println("Turned off the light"); 
		delay = getRandomSleepDelay();
		System.out.println("Sleep delay: " + delay + " milliseconds");
		 System.out.println("======================================");

		Thread.sleep(delay);

		amaranLED_Controller.setTheLightFullOn();
		sendOrder();
		System.out.println("Turned on the light to full intensity"); 
		delay = getRandomSleepDelay();
		System.out.println("Sleep delay: " + delay + " milliseconds");
		System.out.println("======================================");
		Thread.sleep(delay);

 

		for (int i = 1; i <= 10; i++) {
			int frequency = i * 25;
			int red = RANDOM.nextInt(256);
			int green = RANDOM.nextInt(256);
			int blue = RANDOM.nextInt(256);
			int intensity = RANDOM.nextInt(255) + 1; // Between 1 and 255
			int temperature = RANDOM.nextInt(256); // Between 0 and 255

			amaranLED_Controller.setColor(red, green, blue);
			amaranLED_Controller.setIntensity(intensity);
			amaranLED_Controller.setLightTemperature(temperature); 
			amaranLED_Controller.setStrobeEffectFrequency(frequency);
			
			sendOrder();
			
			System.out.println("Set random color (R: " + red + ", G: " + green + ", B: " + blue +
					"), intensity: " + intensity +
					", temperature: " + temperature +
					", and strobe effect frequency: " + frequency); 
			delay = getRandomSleepDelay();
			System.out.println("Sleep delay: " + delay + " milliseconds");
			System.out.println("======================================");
			Thread.sleep(delay);
		}

		amaranLED_Controller.setTheLightOff();
		sendOrder();
		System.out.println("Turned off the light");
		System.out.println("======================================");
	}

	public static void sendOrder() {
		udpSender.sendArray(amaranLED_Controller.getFullFormatted_ArtNet_Packet());
		System.out.println("Sent the Art-Net packet");
	}

	private static int getRandomSleepDelay() {
		return 1000 + RANDOM.nextInt(4000);
	}
}
