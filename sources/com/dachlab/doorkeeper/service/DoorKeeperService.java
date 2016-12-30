package com.dachlab.doorkeeper.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dachlab.doorkeeper.properties.IDoorKeeperProperties;
import com.dachlab.doorkeeper.service.util.PushButtonListener;
import com.dachlab.model.User;
import com.dachlab.service.IGpioService;
import com.dachlab.service.IWebcamService;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;

@Service("doorKeeperService")
public class DoorKeeperService implements IDoorKeeperService {

	final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IWebcamService webcamService;

	private IGpioService gpioService;

	/**
	 * GPIOs declaration.
	 */
	private GpioPinDigitalOutput greenLight;
	private GpioPinDigitalOutput redLight;
	private GpioPinDigitalOutput lock;
	private GpioPinDigitalInput pushButton;

	@Autowired
	public DoorKeeperService(@Qualifier("gpioService") IGpioService gpioService, @Qualifier("doorKeeperProperties") IDoorKeeperProperties doorKeeperProperties) {
		this.gpioService = gpioService;
		// Initialize GPIO accessors if not done already.
		log.info("Initializing the GPIOs for DoorKeeper. gpioService initialized: " + (gpioService == null ? "false" : "true") + ". Green Light is " + doorKeeperProperties
				.getGreenLightPinNumber() + ". RedLight is " + doorKeeperProperties
						.getRedLightPinNumber() + ". Lock is " + doorKeeperProperties.getLockPinNumber() + ". PushButton is " + doorKeeperProperties.getOpenButtonPinNumber() + ".");

		greenLight = gpioService.initDigitalOutputPin(doorKeeperProperties.getGreenLightPinNumber(), "GreenLight", PinState.HIGH, PinState.LOW);
		redLight = gpioService.initDigitalOutputPin(doorKeeperProperties.getRedLightPinNumber(), "RedLight", PinState.HIGH, PinState.LOW);
		lock = gpioService.initDigitalOutputPin(doorKeeperProperties.getLockPinNumber(), "Lock", PinState.HIGH, PinState.LOW);
		pushButton = gpioService.initDigitalInputPin(doorKeeperProperties.getOpenButtonPinNumber(), "pushButton", PinPullResistance.PULL_DOWN, new PushButtonListener(this));

	}

	@Override
	public synchronized void processDoorOpeningRequest() {
		log.info("Door opening request processing ** started **.");

		// Stop blinking.
		gpioService.blink(greenLight, 0, 0);
		gpioService.blink(redLight, 0, 0);

		// Blink both lights while authenticating.
		gpioService.blink(greenLight, 500, 10000);
		gpioService.blink(redLight, 500, 10000);

		// Actually authenticate the user
		User user = webcamService.authenticate();

		if (user != null && user.getUserId() != 0) {
			log.info("Authentication is successfull. User found is " + user + ". Openning the door.");
			// Stop blinking.
			gpioService.blink(greenLight, 0, 0);
			gpioService.blink(redLight, 0, 0);
			// Show green light.
			gpioService.pulse(greenLight, 3000);
			// Open the door.
			gpioService.pulse(lock, 3000);
		} else {
			log.info("Authentication failed.");
			// Stop blinking both lights.
			gpioService.blink(greenLight, 0, 0);
			gpioService.blink(redLight, 0, 0);
			// Show red light.
			gpioService.pulse(redLight, 3000);
		}
		log.info("Door opening request processing ** completed **.");
	}

}
