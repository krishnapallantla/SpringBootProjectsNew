package com.krishna.springassignment.boot.exception;

public class InvalidValueException extends Exception {

	static final long serialVersionUID=745984375943L;
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		//return super.getMessage();
		
		return "Input is expected between 0 and 999999999";
	}

}
