package com.dachlab.doorkeeper.properties;

/**
 * Properties for the DoorKeeper application.
 * 
 * @author dcharles
 *
 */
public interface IDoorKeeperProperties {

	String getOpenButtonPinNumber();

	String getToggleSecurityButtonPinNumber();
	
	String getGreenLightPinNumber();

	String getRedLightPinNumber();

	String getLockPinNumber();

}
