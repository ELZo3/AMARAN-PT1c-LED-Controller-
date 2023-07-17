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

1. Ensure your hardware setup matches the system overview mentioned above.
2. Clone this repository to your local machine.
3. Open the project in your preferred Java IDE.
4. Update the `IP_ADDRESS` and `PORT` in the `ArtNet_AmaranLED_Controller` class to match your setup.
5. Invoke the necessary methods on an instance of the `ArtNet_AmaranLED_Controller` class to control the light. For instance, use the `setIntensity(intensity)` method to modify the light's intensity.

## DMX Channels

Here's a brief explanation of what each DMX channel can control:

1. Channel 1: Intensity (0 - 255)
2. Channel 2: Light temperature (0 - 255) - ranges from 2700 K to 10000 K
3. Channel 5: RGB Red (0 - 255)
4. Channel 6: RGB Green (0 - 255)
5. Channel 7: RGB Blue (0 - 255)
6. Channel 10: Strobe

## Contribution

Contributions are welcome! Feel free to submit a pull request. Please ensure that your code adheres to the existing style for consistency.

## License

This project is released under the MIT License. See the LICENSE file for more details.
