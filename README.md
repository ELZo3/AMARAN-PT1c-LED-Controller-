# Art-Net DMX Lighting Control API for Amaran LED

Welcome to my Art-Net DMX Lighting Control API! This project provides a Java API for controlling the Amaran PT1c LED light through the Art-Net protocol. The communication occurs via an OpenDMX Ethernet DMX512 interface by ENTTEC (ODE Mk2).

## System Overview

Here's a brief overview of the system setup (Screenshots attached):

1. The Amaran PT1c LED light is connected to an ENTTEC OpenDMX Ethernet (ODE Mk2) interface. This interface allows DMX512 signals to be sent over an Ethernet network.
2. The ENTTEC interface is connected via Ethernet to a control PC (IP: 192.168.10.10). The ENTTEC interface's IP configuration is as follows: IP: 192.168.10.20, NetMask: 255.255.255.0, Gateway: 192.168.10.254.
3. The ENTTEC interface is configured as follows: 
Type: Output, Protocol: Art-Net, Universe: 0. This configuration can be accessed and updated via the [ENTTEC Configuration Interface](http://192.168.10.20/index.html). The interface also provides the option to update the firmware.
4. We utilize Java for creating an API that controls the lighting system.

## Usage

Follow these steps to use this project:

1. **Hardware Setup:** Ensure your hardware setup matches the system overview mentioned above.
2. **Clone Repository:** Clone this repository to your local machine.
3. **Java IDE:** Open the project in your preferred Java IDE.
4. **Configuration:** If your ENTTEC interface IP and Port differ from the default (192.168.10.20 and 6454, respectively), adjust them in the `ArtNet_AmaranLED_Controller` constructor within the `DMX_Light_Controller` class. Otherwise, use the default constructor.
5. **Control Lights:** Invoke the necessary methods on an instance of the `ArtNet_AmaranLED_Controller` class to control the light. For instance, use the `setIntensity(intensity)` method to modify the light's intensity.

## DMX Channels

Here's a brief explanation of what each DMX channel can control:

- **Channel 1:** Intensity (0 - 255)
- **Channel 2:** Light temperature (0 - 255) - ranges from 2700 K to 10000 K
- **Channel 5:** RGB Red (0 - 255)
- **Channel 6:** RGB Green (0 - 255)
- **Channel 7:** RGB Blue (0 - 255)
- **Channel 10:** Strobe
  - Value 0: Strobe off. 
  - 1-19: Reserved, strobe off.
  - Values 20 - 255: Control strobe speed, 20 being the slowest (1Hz) and 255 being the fastest (25Hz).


## Contribution

Contributions are welcome! Feel free to submit a pull request. Please ensure that your code adheres to the existing style for consistency.

## License

This project is released under the MIT License. See the LICENSE file for more details.

##  Useful Links

- [Open DMX Ethernet Mk1 ODE (70305/70306)](https://support.enttec.com/support/solutions/articles/101000401530-open-dmx-ethernet-mk1-ode-70305-70306-legacy-product)
- [NMU_1.96.exe (8.43 MB)](https://support.enttec.com/helpdesk/attachments/101012223232)
- [Art-Net 4.pdf](https://www.artisticlicence.com/WebSiteMaster/User%20Guides/art-net.pdf)
- [amaran-PT-DMX-Profile-Specification-V1.0](https://www.aputure.com/wp-content/uploads/2023/02/amaran-PT-DMX-Profile-Specification-V1.0.pdf)
- [amaran-PT-Product-Manual-V1.2-0207](https://www.aputure.com/wp-content/uploads/2023/02/amaran-PT-Product-Manual-V1.2-0207.pdf)





## Screenshots of The LED control software by AMARAN (NMU LED AMARAN) 

![NMU LED AMARAN (3)](https://github.com/ELZo3/AMARAN-PT1c-LED-Controller-/assets/5712013/c032dc1b-e879-4c21-b2f5-a1c1150bfeb7)
![NMU LED AMARAN (2)](https://github.com/ELZo3/AMARAN-PT1c-LED-Controller-/assets/5712013/0964f5e9-5e0b-4cdd-90c8-4dda33e0aa33)
![NMU LED AMARAN (1)](https://github.com/ELZo3/AMARAN-PT1c-LED-Controller-/assets/5712013/3f87c1e2-cb4f-4254-9d6e-3b79aa7313fa)

## Screenshots of The ENTTEC configuration page (ENTTEC ODE MK2- Home)

![ENTTEC ODE MK2 Interface (3)](https://github.com/ELZo3/AMARAN-PT1c-LED-Controller-/assets/5712013/8e378261-00ef-4d8c-9f27-20be3cf448c5)
![ENTTEC ODE MK2 Interface (2)](https://github.com/ELZo3/AMARAN-PT1c-LED-Controller-/assets/5712013/c2031d66-2456-44ab-b9f4-fddc784c2eb2)
![ENTTEC ODE MK2 Interface (1)](https://github.com/ELZo3/AMARAN-PT1c-LED-Controller-/assets/5712013/5bd51b5b-1116-4c13-95ee-85fe571810f3)
