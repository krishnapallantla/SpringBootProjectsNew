package com.krishna.springassignment.boot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.krishna.springassignment.boot.exception.DecimalNotAllowedException;
import com.krishna.springassignment.boot.exception.InvalidValueException;
import com.krishna.springassignment.boot.response.FailedResponse;
import com.krishna.springassignment.boot.response.Response;
import com.krishna.springassignment.boot.response.SuccessResponse;

@Service
public class NumToWordImpl implements NumToWord{

	private static final Logger LOG = LoggerFactory.getLogger(NumToWordImpl.class);
	
	@Override
	public Response getWord(String num) {
		LOG.trace("now executing NumToWordImpl::getWord for num "+ num);
		
		try {
			if (isDecimalNumber(num))
			throw new DecimalNotAllowedException();
			
			LOG.trace("value of num is "+num);
			Long n = Long.parseLong(num);
			LOG.trace("value after parseInt is "+n);
			
			if(n >= MIN_LIMIT && n <= MAX_LIMIT)
				return new SuccessResponse(num, numberToWord(Integer.parseInt(num)));
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
		catch (DecimalNotAllowedException e) {
			LOG.error(e.getMessage());
			
			return new FailedResponse(num, e.getMessage());
		}
		catch (InvalidValueException e) {
			LOG.error(e.getMessage()+" "+MIN_LIMIT+" and "+MAX_LIMIT);
			
			return new FailedResponse(num, e.getMessage()+" "+MIN_LIMIT+" and "+MAX_LIMIT);
		}
		catch (Exception e) {
          LOG.error(e.getMessage());
			e.getStackTrace();

			return new FailedResponse(num, e.getMessage());
		}
}

}
