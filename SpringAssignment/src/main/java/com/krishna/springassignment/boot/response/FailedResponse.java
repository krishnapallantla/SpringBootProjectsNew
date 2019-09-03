package com.krishna.springassignment.boot.response;

import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName(value="Response")
public class FailedResponse implements Response { 
	String num; 
	String word;
  
  public FailedResponse() {}
  
  public void setNum(String num) { this.num = num; }
  
  public void setWord(String word) { this.word = word; }
  
  @Override public String getNum() { 
    return num; 
    }
  
  @Override 
  public String getWord() { 
     return word; 
     }
  
  @Override
  public String toString() { 
	  return "FailedResponse [num=" + num + ", word=" + word + "]"; }
  
  public FailedResponse(String num, String word) { 
	  this.num = num; this.word = word; 
	  } 
  }
 

//@JsonRootName(value="Response")
/*public class FailedResponse extends Response {
	
	public FailedResponse() {
		super("0","zero");
	}
	
	public FailedResponse(String num, String word) {
		super(num,word);

	}

	@Override
	public String toString() {
		return "FailedResponse [num=" + num + ", word=" + word + "]";
	}
	@Override 
	public String getWord() { // TODO Auto-generated method stub 
		return super.getWord();
	}
	
	@Override 
	public String getNum() { // TODO Auto-generated method stub 
		return super.getNum();
	}
	
	public void setNum(String num) { 
		super.num = num; }
	
	public void setWord(String word) { 
		super.word = word; }

}*/