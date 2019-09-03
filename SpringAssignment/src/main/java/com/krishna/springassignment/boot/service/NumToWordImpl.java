package com.krishna.springassignment.boot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.krishna.springassignment.boot.exception.InvalidValueException;
import com.krishna.springassignment.boot.response.FailedResponse;
import com.krishna.springassignment.boot.response.Response;
import com.krishna.springassignment.boot.response.SuccessResponse;

@Service
public strictfp class NumToWordImpl implements NumToWord{

	private static final Logger LOG = LoggerFactory.getLogger(NumToWordImpl.class);
	@Override
	public Response getWord(String num) {
		LOG.trace("now executing NumToWordImpl::getWord for num "+ num);
		try {
			
			LOG.info("value of num is "+num);
			Long n = Long.parseLong(num);
			LOG.info("value after parseInt is "+n);
			
			if(n > -1 && n <= 999999999)
				return new SuccessResponse(num, convert(Integer.parseInt(num)));
			else 
				throw new InvalidValueException();
		}
		catch (NumberFormatException e) {
			e.getStackTrace();
			System.out.println("*** " + e.getMessage());
			LOG.error("not a valid number");
			LOG.error(e.getMessage());
			
			return new FailedResponse(num, "not a valid number");
		}
		catch (InvalidValueException e) {
			System.out.println("*** " + e.getMessage());
			LOG.error("number not between 0 and 999999999");
			LOG.error(e.getMessage());
			
			return new FailedResponse(num, e.getMessage());
			
			
		}
		catch (Exception e) {
			
          System.out.println("*** " + e.getMessage());
          LOG.error(e.getMessage());
          
          return new FailedResponse(num, e.getMessage());
			
		
		}
		
}

}
