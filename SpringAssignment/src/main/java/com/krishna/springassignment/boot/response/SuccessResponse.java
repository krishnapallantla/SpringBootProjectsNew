package com.krishna.springassignment.boot.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;


  //@XmlRootElement(name="Response")
@JsonRootName(value="Response")
public class SuccessResponse implements
  Response {
  
  String num; String word;
  
  public SuccessResponse() {}
  
  public void setNum(String num) { 
	  this.num = num; 
	  }
  
  public void setWord(String word) { this.word = word; }
  
  @Override 
  public String getNum() { 
	  return num; }
  
  @Override 
  public String getWord() { 
return word; 
}
  
  public SuccessResponse(String num, String word) { 
	  this.num = num; 
	  this.word = word; }
  
  @Override 
  public String toString() { 
	  return "SuccessResponse [num=" + num +  ", word=" + word + "]"; 
	  }
    
  }
 

/*
 * @JsonRootName(value="Response") public class SuccessResponse extends Response
 * {
 * 
 * public SuccessResponse() { super("0","zero"); }
 * 
 * public SuccessResponse(String num, String word) { super(num,word);
 * 
 * }
 * 
 * @Override public String toString() { return "SuccessResponse [num=" + num +
 * ", word=" + word + "]"; }
 * 
 * @Override public String getWord() { // TODO Auto-generated method stub return
 * super.getWord(); }
 * 
 * @Override public String getNum() { // TODO Auto-generated method stub return
 * super.getNum(); }
 * 
 * public void setNum(String num) { super.num = num; }
 * 
 * public void setWord(String word) { super.word = word; }
 * 
 * 
 * @ResponseStatus(HttpStatus.BAD_REQUEST, reason =
 * "Some parameters are invalid") void
 * onIllegalArgumentException(IllegalArgumentException exception) {}
 * 
 * }
 */