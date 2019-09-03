package com.krishna.springassignment.boot.controller.rest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.krishna.springassignment.boot.response.Response;
import com.krishna.springassignment.boot.service.NumToWord;
import com.krishna.springassignment.boot.service.NumToWordImpl;

@RestController
@Service
public strictfp class WebController {

	@Autowired
	NumToWord serviceObj;

    private static final Logger LOG = LoggerFactory.getLogger(WebController.class);	
	
	@GetMapping(value="/api/num/{num}", produces={"application/json"})
	public Response getWord(@PathVariable("num") String num) {
	
	LOG.info("received request for "+ "http://localhost:8081/api/num/="+num);

	try {
		Response resp = serviceObj.getWord(String.valueOf(num));
		LOG.info("after conversion at service class "+resp.getNum()+" "+resp.getWord());
		return(resp);
	} catch (NullPointerException e) {
		LOG.info("Autowired don't execute for JUnit testCase and this is the work around for nullpointer exception ");
		LOG.info("creating instance for NumToWordImpl()");
		return new NumToWordImpl().getWord(String.valueOf(num));
	}catch (Exception e) {
	    e.printStackTrace();
		LOG.error("during getWord execution "+e.getStackTrace());
		return null;
	}
			
	}
	
	@GetMapping(value="/api/nums/{num}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Response> getWords(@PathVariable("num") String nums) {
		
		LOG.trace("received request for "+ "http://localhost:8081/api/nums/="+nums);
		
		List<String> input = Arrays.asList(nums.toString().split(":"));
		List<Response> responses = new LinkedList<Response>();
		
		try{
			input.forEach(n -> responses.add(serviceObj.getWord(n)));
		LOG.trace(responses.toString());
		return responses;
		
	} catch (NullPointerException e) {
		LOG.info("Dependency Injection don't execute for JUnit testCase and this is the work around for nullpointer exception ");
		LOG.info("creating instance for NumToWordImpl()");
		input.forEach(n -> responses.add(new NumToWordImpl().getWord(n)));
		return responses;
	}catch (Exception e) {
	    e.printStackTrace();
		LOG.error("during getWord execution "+e.getStackTrace());
		return null;
	}
	}
	
	@GetMapping(value="/api/n", produces=MediaType.APPLICATION_JSON_VALUE)
	public String getW() {

    return("test");
			
	}
	
/*	class Resp{
		private String num;
		private String word;
		
		public Resp(String num, String word) {
			super();
			this.num = num;
			this.word = word;
		}
		
		public String getNum() {
			return num;
		}
		public void setNum(String num) {
			this.num = num;
		}
		public String getWord() {
			return word;
		}
		public void setWord(String word) {
			this.word = word;
		}
		
	}
*/
}
