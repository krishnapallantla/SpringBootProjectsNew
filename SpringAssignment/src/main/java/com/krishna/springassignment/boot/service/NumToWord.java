package com.krishna.springassignment.boot.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import com.krishna.springassignment.boot.response.Response;

@Component
public interface NumToWord {

static int MAX_LIMIT = 999999999;
static int MIN_LIMIT = 0;
	
String[] thousandthMultiplePosition = {""," thousand"," million"," billion"," trillion"," quadrillion"," quintillion"};
	    
String[] tenthPosition = {""," ten"," twenty"," thirty"," forty"," fifty"," sixty"," seventy"," eighty"," ninety"};
	    
String[] unitToNinenteenPosition = {""," one"," two"," three"," four"," five"," six"," seven"," eight"," nine",
	        " ten"," eleven"," twelve"," thirteen"," fourteen"," fifteen"," sixteen"," seventeen"," eighteen",
	        " nineteen"};
	    
default String numberToWord(int number) {

    if (number == 0)
        return "zero";
    
    String prefix = "";
    
    if (number < 0) {
        number = -number;
        prefix = "negative";
    }
    
    String current = "";
    int place = 0;
    
    do {
        int n = number % 1000;
        if (n != 0){
            String s = numberBelowThousand(n);
            current = s + thousandthMultiplePosition[place] + current;
        }
        place++;
        number /= 1000;
    } while (number > 0);
    
    return (prefix + current).trim();
}

default String numberBelowThousand(int number) {
	        String current;
	        
	        if (number % 100 < 20){
	            current = unitToNinenteenPosition[number % 100];
	            number /= 100;
	        }
	        else {
	            current = unitToNinenteenPosition[number % 10];
	            number /= 10;
	            
	            current = tenthPosition[number % 10] + current;
	            number /= 10;
	        }
	        if (number == 0) return current;
	        return unitToNinenteenPosition[number] + " hundred" + current;
	    }
	    
Response getWord(String num);
default boolean isDecimalNumber(String num) {
	
	Pattern pat = Pattern.compile("^[0-9]*\\.[0-9]+$");
	Matcher mat = pat.matcher(num);

	 if (mat.matches()) 
	 	return true;
	 
	 return false;
}

}
