package com.dachlab.doorkeeper.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Properties for the DoorKeep application.
 * 
 * @author dcharles
 *
 */
@Component("doorKeeperProperties")
@ConfigurationProperties(prefix = "doorkeeper")
public class DoorKeeperProperties implements IDoorKeeperProperties {

	private String openButtonPinNumber;
	private String toggleSecurityButtonPinNumber;
	private String greenLightPinNumber;
	private String redLightPinNumber;
	private String lockPinNumber;

	@Override
	public String getOpenButtonPinNumber() {
		return openButtonPinNumber;
	}

	@Override
	public String getToggleSecurityButtonPinNumber() {
		return toggleSecurityButtonPinNumber;
	}

	@Override
	public String getGreenLightPinNumber() {
		return greenLightPinNumber;
	}

	@Override
	public String getRedLightPinNumber() {
		return redLightPinNumber;
	}

	@Override
	public String getLockPinNumber() {
		return lockPinNumber;
	}

	public void setLockPinNumber(String lockPinNumber) {
		this.lockPinNumber = lockPinNumber;
	}

	public void setOpenButtonPinNumber(String openButtonPinNumber) {
		this.openButtonPinNumber = openButtonPinNumber;
	}

	public void setGreenLightPinNumber(String greenLightPinNumber) {
		this.greenLightPinNumber = greenLightPinNumber;
	}

	public void setRedLightPinNumber(String redLightPinNumber) {
		this.redLightPinNumber = redLightPinNumber;
	}

	public void setToggleSecurityButtonPinNumber(String toggleSecurityButtonPinNumber) {
		this.toggleSecurityButtonPinNumber = toggleSecurityButtonPinNumber;
	}

}
