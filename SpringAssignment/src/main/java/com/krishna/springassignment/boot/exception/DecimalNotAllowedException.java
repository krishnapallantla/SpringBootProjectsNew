package com.krishna.springassignment.boot.exception;

public class DecimalNotAllowedException extends Exception {

	static final long serialVersionUID=745984375947L;
	
	@Override
	public String getMessage() {

		return "Decimal numbers not considered for conversion";
	}

}
