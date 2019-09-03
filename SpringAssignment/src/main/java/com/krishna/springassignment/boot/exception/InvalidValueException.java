package com.krishna.springassignment.boot.exception;

public class InvalidValueException extends Exception {

	static final long serialVersionUID=745984375943L;
	
	@Override
	public String getMessage() {

		return "Number not in the range";
	}

}
