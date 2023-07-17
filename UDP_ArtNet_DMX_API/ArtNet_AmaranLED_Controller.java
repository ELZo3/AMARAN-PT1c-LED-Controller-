package UDP_ArtNet_DMX_API;

/*
Here’s the protocol in format. It starts from Byte [18] --> Byte [28]
Channe:      Value    Percent     Function    Function 

Channel 1:  0 - 255    0 - 100       Intensity  
Channel 2:  0 - 255      0 - 100      Light temperature          2700 K - 10000 K
Channel 3:  always 0   		 
Channel 4:  always 255
Channel 5:  0 - 255    0 - 100    (RGB RED)    0 - 100%
Channel 6:  0 - 255    0 - 100    (RGB GREEN)    0 - 100%
Channel 7:  0 - 255    0 - 100    (RGB BLUE)    0 - 100%
Channel 8:  always 0		 Reserved for Future Use    
Channel 9:  always 0		 Reserved for Future Use   
Channel 10:  0 - 19    0 - 7    Strobe    "No Effect”  
Channel 10: 20 - 255    8 - 100%    Strobe    1 Hz - 25 Hz

 */

import java.util.Random;

/**
 * This class represents an Art-Net packet for controlling lighting devices.
 */

//===============================================================================================================
//===============================================================================================================
public class ArtNet_AmaranLED_Controller {
	public int[] amaranPT_channels;
	private Random random;
	public byte[] ArtNet_Packet ;  // New field for the Art-Net packet
	public String IP_ADDRESS = "192.168.10.20";
	public int PORT = 6454; 
	//=================================================================== 
	/**
	 * Default constructor initializes the channel values to their default states according to the protocol.
	 */
	public ArtNet_AmaranLED_Controller() {
		this.amaranPT_channels = new int[10];
		this.random = new Random(); 
		resetToDefaults(); 
		this.ArtNet_Packet = formatArtNetPacket(); 
	}
	//===================================================================  
	public ArtNet_AmaranLED_Controller(String _IP_ADDRESS,int _PORT ) {
		this.amaranPT_channels = new int[10];
		this.random = new Random(); 
		resetToDefaults(); 
		this.ArtNet_Packet = formatArtNetPacket(); 
		this.IP_ADDRESS = _IP_ADDRESS;
		int PORT =   _PORT;
	}
	//=================================================================== 
	/**
	 * Reset channel values to default state
	 */
	void resetToDefaults() {
		setIntensity(0); // Intensity
		setLightTemperature(0); // Light temperature
		setToRGB();
		setRed(0); // RGB Red
		setGreen(0); // RGB Green
		setBlue(0); // RGB Blue
		setStrobeEffectFrequency(0); // Strobe
	}
	//=================================================================== 
	public void setTheLightOff() {
		setIntensity(0); // Intensity 
	} 
	//=================================================================== 
	public  void setTheLightFullOn() { 
		setIntensity(255); // Intensity 
	}
	//=================================================================== 
	/**
	 * Set the intensity of the light
	 */
	public void setIntensity(int intensity) {
		amaranPT_channels[0] = clamp(intensity);
	}
	//=================================================================== 
	/**
	 * Set the light temperature
	 */
	public void setLightTemperature(int temperature) {
		amaranPT_channels[1] = clamp(temperature);
	}
	//=================================================================== 
	/**
	 * Set  Crossfade to Color //  CCT <-->  RGB // 0 - 255 // 0 - 100 
	 */
	public void setCrossfadeToColor(int crossfadeToColor) {
		amaranPT_channels[3] = clamp(crossfadeToColor);
	}
	//=================================================================== 
	/**
	 * Set the light temperature
	 */
	public void setToRGB() {
		setCrossfadeToColor (255);
	}
	//=================================================================== 
	/**
	 * Set the RGB red value
	 */
	public void setRed(int red) {
		amaranPT_channels[4] = clamp(red);
	}
	//=================================================================== 
	/**
	 * Set the RGB green value
	 */
	public void setGreen(int green) {
		amaranPT_channels[5] = clamp(green);
	}
	//=================================================================== 
	/**
	 * Set the RGB blue value
	 */
	public void setBlue(int blue) {
		amaranPT_channels[6] = clamp(blue);
	} 

	//===================================================================  
	public void turnOnRedLight( ) {
		resetToDefaults();
		setRed (255);
		setTheLightFullOn(); 
		 
	} 
	//=================================================================== 
	public void turnOnBlueLight( ) {
		resetToDefaults();
		setBlue(255);
		setTheLightFullOn();
	} 
	//=================================================================== 
	public void turnOnGreenLight( ) {
		resetToDefaults();
		setGreen (255);
		setTheLightFullOn();
	}  
	//===================================================================  
	public void turnOnBlinkingRedLight( ) {
		resetToDefaults();
		setRed (255);
		setStrobeEffectFrequency(150);
		setTheLightFullOn(); 
	} 
	//=================================================================== 
	public void turnOnBlinkingBlueLight( ) {
		resetToDefaults();
		setBlue(255);
		setStrobeEffectFrequency(150);
		setTheLightFullOn();
	} 
	//=================================================================== 
	public void turnOnBlinkingGreenLight( ) {
		resetToDefaults();
		setGreen (255);
		setStrobeEffectFrequency(150);
		setTheLightFullOn();
	}  
	//=================================================================== 
	/**
	 * Set the strobe effect frequency
	 */
	public void setStrobeEffectFrequency(int frequency) {
		amaranPT_channels[9] = clamp(frequency);
	}
	//=================================================================== 
	/**
	 * Get the current light intensity
	 */
	public int getIntensity() {
		return amaranPT_channels[0];
	}
	//=================================================================== 
	/**
	 * Get the current light temperature
	 */
	public int getLightTemperature() {
		return amaranPT_channels[1];
	}
	//=================================================================== 
	/**
	 * Get the current RGB red value
	 */
	public int getRed() {
		return amaranPT_channels[4];
	}
	//=================================================================== 
	/**
	 * Get the current RGB green value
	 */
	public int getGreen() {
		return amaranPT_channels[5];
	}
	//=================================================================== 
	/**
	 * Get the current RGB blue value
	 */
	public int getBlue() {
		return amaranPT_channels[6];
	}
	//=================================================================== 
	/**
	 * Get the current strobe effect frequency
	 */
	public int getStrobeEffectFrequency() {
		return amaranPT_channels[9];
	}
	//===================================================================  
	/**
	 * Set the RGB color
	 */
	public void setColor(int red, int green, int blue) {
	    setRed(clamp(red));
	    setGreen(clamp(green));
	    setBlue(clamp(blue));
	} 
	//===================================================================  
	/**
	 * Set a random color
	 * @return 
	 */
	public int[] setRandomColor() {
		setRed(random.nextInt(256));
		setGreen(random.nextInt(256));
		setBlue(random.nextInt(256));
		return amaranPT_channels;
	}
	//=================================================================== 
	/**
	 * Format the channel values into a complete Art-Net packet.
	 */
	public byte[] formatArtNetPacket() {
		byte[] ArtDmx_Packet = new byte[530];

		// Set up the header for the Art-Net packet

		//The ID field is 8 bytes  null terminated string of ASCII
		ArtDmx_Packet[0] = 'A';
		ArtDmx_Packet[1] = 'r';
		ArtDmx_Packet[2] = 't';
		ArtDmx_Packet[3] = '-';
		ArtDmx_Packet[4] = 'N';
		ArtDmx_Packet[5] = 'e';
		ArtDmx_Packet[6] = 't'; 
		ArtDmx_Packet[7] = 0; 

		// OpCode field is a 16-bit word transmitted low byte first. The OpCode identifies the packet type. In the ArtDmx packet this field is set to ‘OpDmx’.
		// Opcode ArtDMX (0x5000) little endian
		ArtDmx_Packet[8] = 0x00;  //Lo
		ArtDmx_Packet[9] = 0x50;  //Hi

		// ProtVer 16-bit word of the Art-Net protocol revision number. Low byte of the Art-Net protocol revision  number. Current value 14.
		ArtDmx_Packet[10] = 0;  //Hi
		ArtDmx_Packet[11] = 14; //Lo

		// The Sequence field is an 8-bit number that is designed to show the order in which packets were originated.
		ArtDmx_Packet[12] = 0; 


		// The Physical field is an 8-bit number that defines the physical port that generated the packet.
		//It is intended to be purely informative and is not used to define the destination of the packet.
		ArtDmx_Packet[13] = 0; 


		//  Net and SubUni are combined to form the 15-bit Port-Address to which this packet is directed.
		ArtDmx_Packet[14] = 0;   //  SubUni 
		ArtDmx_Packet[15] = 0; // Net

		// Length: The Length field is a 16-bit number transmitted most significant byte first. It defines the number of bytes encoded in the Data[] field
		ArtDmx_Packet[16] = 0x02;
		ArtDmx_Packet[17] = 0x00;


		// ArtDmx_Packet[17] -->  ArtDmx_Packet[530]; 
		// Data: The Data field contains the data slot (channel levels). The size of this array is defined by the Length field. 

		// we fill bytes from 18 to 530 by 0s 
		for (int i = 18; i < ArtDmx_Packet.length; i++) {
			ArtDmx_Packet[i] = (byte) 0x00;
		}

		// Set specific channel values, from byte 18 to the necessary bytes we need to control our Amaran LED
		for (int i = 0; i < amaranPT_channels.length; i++) {
			ArtDmx_Packet[i + 18] = (byte) amaranPT_channels[i];
		}

		return ArtDmx_Packet;
	}

	//=================================================================== 
	//===================================================================  
	/**
	 * Get the  Fully Formatted ArtNet Packet of byte[530]
	 */
	public byte[] getFullFormatted_ArtNet_Packet () {
		this.ArtNet_Packet = formatArtNetPacket();
		return  this.ArtNet_Packet;
	}
	//=================================================================== 
	/**
	 * Ensures value is within 0 - 255 range
	 */
	public int clamp(int value) {
		return Math.max(0, Math.min(255, value));
	}
	//=================================================================== 

}
