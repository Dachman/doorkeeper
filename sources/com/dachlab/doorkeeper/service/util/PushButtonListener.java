package com.dachlab.doorkeeper.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dachlab.doorkeeper.service.IDoorKeeperService;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * 
 * @author dcharles Listener for DoorKeeper purpose.
 */
public class PushButtonListener implements GpioPinListenerDigital {

	IDoorKeeperService doorKeeperService;
	final Logger log = LoggerFactory.getLogger(this.getClass());

	public PushButtonListener(IDoorKeeperService doorKeeperService) {
		this.doorKeeperService = doorKeeperService;
	}

	@Override
	public synchronized void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		if (event.getState().equals(PinState.HIGH)) {
			log.info("Button pushed ! Event fired from pin " + event.getPin().getName() + ". State is " + event.getState() + ".");
			doorKeeperService.processDoorOpeningRequest();
		}
	}

}
