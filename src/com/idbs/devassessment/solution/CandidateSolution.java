/*
 * Copyright (C) 1993-2020 ID Business Solutions Limited
 * All rights reserved
 */
package com.idbs.devassessment.solution;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.idbs.devassessment.core.IDBSSolutionException;
import com.idbs.devassessment.harness.DigitalTaxTracker;
import com.idbs.devassessment.core.DifficultyLevel;

/**
 * Example solution for the example question
 */

public class CandidateSolution extends CandidateSolutionBase
{
    
	 
	 
	 int xValue =0;                                     //global variable used to store the value of x 
	 int power[] = new int[10];                         //global variable for the array with the exponents of x in the polynomial
     int multiplier[] = new int[10];                    // variable for the array with the multipliers used in the polynomial
     String action[] = new String[10];                  // variable  used to store the strings representing addition and subtraction in the polynomial
     
     long powerArray[] = new long[10];                  //variable used to store the values given by x raised to the powers in the polynomial
     
     long powerArray3D[][][] = new long[51][10][11];    //3-dimensional array used to store the value of each term of the polynomial encountered in the tests
	                                                    //meaning multiplying the multiplier with the corresponding power of x 
    
    @Override
    public DifficultyLevel getDifficultyLevel()
    {
        /*
         * 
         * CHANGE this return type to YOUR selected choice of difficulty level to which you will code an answer to.
         * 
         */

        return DifficultyLevel.LEVEL_3;
    }

    
    public int value(String s) {                                      //method used to extract the value of x from the json document for LEVEL 1 and LEVEL 2
    	JsonReader reader = Json.createReader(new StringReader(s));    
        JsonObject jsonObject = reader.readObject();
        

        // get the start value from the Json
        int xValue = jsonObject.getInt("xValue");
        return xValue;
    }
    
    
    
    public int value2(String s) {                               //another method to extract the value of x when we have a numeric format
    	
    	xValue = Character.getNumericValue(s.charAt(pos(s)));    //extract the value of x in case it is a single digit number
        
			int o = xValue;
			if(s.charAt(pos(s)+1) != ";".charAt(0)) {            //check if x has two digits
				for(int j=0;j<9;j++) {
			    	  xValue = xValue + o;
			    	 
				}
					
			    xValue += Character.getNumericValue(s.charAt(pos(s)+1));     //if x has two digits we multiply the first digit ten times and add the second one
			}
			  
		return xValue;
    }
    
    
    public int pos(String s) {                             //method used to find the position of a number after "=" checking if there are blank spaces 
    	
    	 int position =0;
		 
		  for(int i=0;i< s.length(); i++) {
	        	if(s.charAt(i) == "=".charAt(0)) {
	        		position=i;
	        		position++;

	        		while(s.charAt(position) == " ".charAt(0)){
	        		    position++;
	        		    
	        		}
	        	    
	        	    break;
	        	    
	        	 }
	       }
			
    	return position;
    }
    
    public int posLEVEL3(String s) {         //same as pos method just using DigitalTaxTracker methods for addition and subtraction
    	
   	 int position =0;
		 
		  for(int i=0;i< s.length(); i++) {
	        	if(s.charAt(i) == "=".charAt(0)) {
	        		position=i;
	        		position = DigitalTaxTracker.add(position,1);

	        		while(s.charAt(position) == " ".charAt(0)){
	        		    position = DigitalTaxTracker.add(position,1);
	        		    
	        		}
	        	    break;
	        	    
	        	}
	        }
			
   	return position;
   }
    
    
    public int[] powerfunction(String s) {                              //method used to extract the exponents of x from a json document and store them in the array power
    	JsonReader reader = Json.createReader(new StringReader(s));
        JsonObject jsonObject = reader.readObject();
        
        JsonArray jsonArray = jsonObject.getJsonArray("terms");
        
        for (int i = 0; i < jsonArray.size(); i++)
        {
           
           JsonObject item = jsonArray.getJsonObject(i);                    //get a JsonObject from the JsonArray
           power[i] = item.getInt("power");                                 //get the integer value for "power" from each JsonObject
           
        }
        return power;
    }
    
	
    public int[] multiplierfunction(String s) {                         //function used to extract the multipliers from the json document
    	JsonReader reader = Json.createReader(new StringReader(s));     //and store them in the array multiplier
        JsonObject jsonObject = reader.readObject();
        
        JsonArray jsonArray = jsonObject.getJsonArray("terms");
        
        for (int i = 0; i < jsonArray.size(); i++)
        {
           
           JsonObject item = jsonArray.getJsonObject(i); 
           multiplier[i] = item.getInt("multiplier");
           
        }
        return multiplier;
    }
    
  
    
    public int[] multiplierfunction2(String s) {                       //method used to extract the multipliers when we have a numeric format
    	   int index =0;
           for(int i=pos(s); i<s.length(); i++) {
              if(s.charAt(i) == ".".charAt(0) || (s.charAt(i) == "x".charAt(0) && s.charAt(i-1)!= ".".charAt(0))) {  //we check for dots because the multiplier is on the position behind
           	    if(s.charAt(i-1) == "0".charAt(0) && s.charAt(i-2) == "1".charAt(0)){                                //we check if the multiplier is 10 because otherwise it has one digit
           	        multiplier[index] =10;
           	        index++;
           	    }
           	    
           	    
           	   else{
           	    
           	  multiplier[index] = Character.getNumericValue(s.charAt(i-1));                 //store the value of the multiplier in case it has only one digit
           	  index++;
           	   }
           	}
           	
           }
             return multiplier;
    }
	
    
    public String[] actionfunction(String s) {                              //method for getting the actions representing addition or subtraction from the json document
    	JsonReader reader = Json.createReader(new StringReader(s));
        JsonObject jsonObject = reader.readObject();
        
        JsonArray jsonArray = jsonObject.getJsonArray("terms");
        
        for (int i = 0; i < jsonArray.size(); i++)
        {
           
           JsonObject item = jsonArray.getJsonObject(i); 
           action[i] = item.getString("action");
           
        }
        return action;
    }
	
    
    public String[] actionfunction2(String s) {                     //method for getting the actions when we have numerical format
    	
    	 int index =0;
         for(int i=pos(s); i< s.length(); i++) {                    //we loop through the String and check if characters are "+" or "-"
         	if(s.charAt(i) == "+".charAt(0)) {
         		action[index] = "+";
         		index++;
         		
         	}
         	else if(s.charAt(i) == "-".charAt(0)) {
         		action[index] = "-";
         		index++;
         	    
         }
			   
		}
         return action;
    }
    
    
    public int getLength(String s) {                                    //method for getting the length of the JsonArray of "terms"
    	JsonReader reader = Json.createReader(new StringReader(s));
        JsonObject jsonObject = reader.readObject();
        
        JsonArray jsonArray = jsonObject.getJsonArray("terms");
        
        return jsonArray.size();
    }
    
	
    public Integer getMaximum(int array[]) {                           //function returning the maximum value from an array
    	int m = -1000;
        for(int i=0;i < array.length; i++){
          if(array[i]>m){
            m = array[i];
          }
        }
    	
    	return m;
    }
    

    public void pArray(int x, int array[],int size) {         //method for storing all the powers of a given x with the exponent at most the maximum of a given array
    	int Max = getMaximum(array);
        
    	for(int i=0;i< size; i++){
          powerArray[i] =0;                             //we store the powers of x in powerArray
        }
        powerArray[0] = 1;
        powerArray[1] = x;
        for(int i=0; i<x; i++)
          powerArray[2] = powerArray[2]+x;
       
        int k=3;
        while(k<=Max){
            if(k%2==1){
              for(int i=0;i<powerArray[1];i++)
               powerArray[k] += powerArray[k-1];          //if the exponent is odd then we calculate the power recursively multiplying the precedent power with x 
            }
            else{
              for(int i=0;i<powerArray[2];i++)           //if x is even we multiply x to the power of k-2 with x to the power of 2
               powerArray[k] += powerArray[k-2];
            }
            k++;
        }
    }
    
   
    public Long polynom(int x, int array1[] , int array2[] , String []array3 , int len) {      //we compute the value of the polynomial multiplying the powers of x
                                                                                               //with the multipliers and adding or subtracting them from the answer   	

    	pArray(x,array1,len);
        
    	long answer=0;
        for(int i=0; i< len; i++){
          if(array3[i].equals("add") || array3[i] == "+"){
            for(int j=0;j< array2[i]; j++)
              answer += powerArray[array1[i]];
              
          }
          else{
            for(int j=0;j< array2[i]; j++)
              answer -= powerArray[array1[i]];
          }
        }
          return answer;
        }
    	
    
    
    
   public int value2LEVEL3(String s) {                    //same as value2 method just using DigitalTaxTracker methods for addition and subtraction
    	
    	xValue = Character.getNumericValue(s.charAt(posLEVEL3(s)));
        
			int o = xValue;
			if(s.charAt(DigitalTaxTracker.add(posLEVEL3(s),1)) != ";".charAt(0)) {
				for(int j=0;j<9;j++) {
			    	  xValue = DigitalTaxTracker.add(xValue,o);
			    	 
				}
					
			
			xValue = DigitalTaxTracker.add(xValue,Character.getNumericValue(s.charAt(DigitalTaxTracker.add(posLEVEL3(s),1))));
			}
			  
		return xValue;
    }
       
    
    public int[] multiplierfunction2LEVEL3(String s) {        //it is the same as multiplierfunction2 just using the methods of DigitalTaxTracker for LEVEL3
    	   int index =0;
           for(int i=posLEVEL3(s); i<s.length(); i++) {
              if(s.charAt(i) == ".".charAt(0) || (s.charAt(i) == "x".charAt(0) && s.charAt(DigitalTaxTracker.substract(i, 1))!= ".".charAt(0))) {
           	    if(s.charAt(DigitalTaxTracker.substract(i,1)) == "0".charAt(0) && s.charAt(DigitalTaxTracker.substract(i,2)) == "1".charAt(0)){
           	        
			        multiplier[index] =10;
           	        index =DigitalTaxTracker.add(index,1);

           	    }
           	    
           	    
           	    else{
           	    
           	  multiplier[index] = Character.getNumericValue(s.charAt(DigitalTaxTracker.substract(i,1)));
           	  index =DigitalTaxTracker.add(index,1);
           	    }
           	}
           	
           }
             return multiplier;
    }
	
	
    public String[] actionfunction2LEVEL3(String s) {         //same as actionfunction2 but using DigitalTaxTracker methods for LEVEL3
    	
    	 int index =0;
         for(int i=posLEVEL3(s); i< s.length(); i++) {
         	if(s.charAt(i) == "+".charAt(0)) {
         		action[index] = "+";
         		index =DigitalTaxTracker.add(index,1);
         		
         	}
         	else if(s.charAt(i) == "-".charAt(0)) {
         		action[index] = "-";
         		index =DigitalTaxTracker.add(index,1);
         	    
         }
			   
		}
         return action;
    }
   
    
    
    public void pArrayLEVEL3(int x, int array[],int size) {       //pArray method for LEVEL3 using powerArray3D to store the terms of polynomial along the way
    	                                                          //and reuse them in case they are encountered again
    	int Max = getMaximum(array);                                      
        
    	
        for(int i=0;i< size; i++){
          powerArray[i] =0;
        }
        
        powerArray3D[x][0][1] = 1;
        powerArray3D[x][1][1] = x;
        	
        if(powerArray3D[x][2][1] == 0) {
        	 for(int i=0; i<x; i++)
        		 powerArray3D[x][2][1] = DigitalTaxTracker.add(powerArray3D[x][2][1],x);
        	
        }
       

        for(int k=3;k<=Max;k++){
            
            	if(powerArray3D[x][k][1] == 0) {                                          //we check if we met x to the power of k multiplied by one
            		for(int i=0;i<powerArray3D[x][1][1];i++)
            			powerArray3D[x][k][1] = DigitalTaxTracker.add(powerArray3D[x][k][1],powerArray3D[x][DigitalTaxTracker.substract(k,1)][1]);      //if not we compute x to the power of k multiplied by one
            		                                                                                                       //and store it in powerArray3D on position (x,k,1)
               	
               }
           }
    }
   
    
    public Long polynomLEVEL3(int x, int array1[] , int array2[] , String []array3 , int len) {

    	pArrayLEVEL3(x, array1,len);
        
    	long answer=0;
        for(int i=0; i< len; i++){
          if(array3[i].equals("add") || array3[i] == "+"){
             long v=0;
        	 if(powerArray3D[x][array1[i]][array2[i]] == 0) {                        //we check if we have stored x to the power of array1[i] multiplied by array2[i]
            	for(int j=0;j< array2[i]; j++)
                   v = DigitalTaxTracker.add(v,powerArray3D[x][array1[i]][1]);       //if not we compute it by multiplying array2[i] with x to the power array1[i]  
            	answer = DigitalTaxTracker.add(answer,v);                            //multiplied by one
            	powerArray3D[x][array1[i]][array2[i]] = v;                           //and assign it to powerArray3D[x][array1[i]][array2[i]]
          }
        	 
        	 else {
        		 answer = DigitalTaxTracker.add(answer,powerArray3D[x][array1[i]][array2[i]]);    //else we add to the answer the value stored for
        		                                                                                  //x to the power of array1[i] multiplied by array2[i]
        	 }
          }
          
          else{
        	  
           	   
        	 long v=0;
         	 if(powerArray3D[x][array1[i]][array2[i]] == 0) {
             	for(int j=0;j< array2[i]; j++)
                    v = DigitalTaxTracker.substract(v,powerArray3D[x][array1[i]][1]);
             	answer = DigitalTaxTracker.add(answer,v);
             	powerArray3D[x][array1[i]][array2[i]] = v;
           }
         	
              	
         	else {
        		 answer = DigitalTaxTracker.substract(answer,powerArray3D[x][array1[i]][array2[i]]);
        	 }
             
                
          }
        }
          return answer;
      }   
    
    
    @Override
    public String getAnswer() throws IDBSSolutionException
    {
        
    	String json  = getDataForQuestion();
    	
    	
    	
    	if((getDifficultyLevel()==  DifficultyLevel.LEVEL_1) || (getDifficultyLevel()==  DifficultyLevel.LEVEL_2 && (json.charAt(0) == "j".charAt(0)))) {
    	
    		if(json.charAt(0) == "j".charAt(0)) {                        //if we have the case in LEVEL2 where the input String starts with "json:" we 
    			json = json.substring(5);                                //extract the String having the actual json document structure
    		}

         return Long.toString(polynom(value(json),powerfunction(json),multiplierfunction(json),actionfunction(json),getLength(json)));
       
       
    	}
    	
    	else if((getDifficultyLevel()==  DifficultyLevel.LEVEL_2) && (json.charAt(0) == "n".charAt(0))) {
    		 
    		 int index =0;
  	         for(int i=14; i< json.length(); i++) {
  	         	if(json.charAt(i) == "^".charAt(0)) {
  	         	  power[index] = Character.getNumericValue(json.charAt(i+1));    //in case we have numerical format we have to extract the values for power here
  	           	  index++;                                                       //in order to retain the length of the array of terms, parameter required for
  	 				                                                             //the method polynom
  	         	}
  	         }
           
             int l =index;
    	                
    	     return Long.toString(polynom(value2(json),power,multiplierfunction2(json),actionfunction2(json),l));
    	 
    	}
    	    	   
    	else if(getDifficultyLevel()==  DifficultyLevel.LEVEL_3) {
    		
    		if(json.charAt(0) == "j".charAt(0)) {
    			json = json.substring(5);
    			return Long.toString(polynomLEVEL3(value(json),powerfunction(json),multiplierfunction(json),actionfunction(json),getLength(json)));
    	        
    		}
    		
    		else {
    			
    			int index =0;
     	         for(int i=14; i< json.length(); i++) {
     	         	if(json.charAt(i) == "^".charAt(0)) {
     	         	  power[index] = Character.getNumericValue(json.charAt(DigitalTaxTracker.add(i, 1)));
     	         	  index = DigitalTaxTracker.add(index, 1);
     	 				
     	         	}
     	         }
              
                int l =index;
               
                
        	      
              return Long.toString(polynomLEVEL3(value2LEVEL3(json),power,multiplierfunction2LEVEL3(json),actionfunction2LEVEL3(json),l));
        	 
    	
    	}
    	}
    	
    	return null;
    	
    }
    
     public String getAns(String json) {        //same method as getAnswer() but takes as parameter a String instead of using getDataForQuestion()
    	                                        //as input. Created for being used in Junit tests
    	
    	
    	if((getDifficultyLevel()==  DifficultyLevel.LEVEL_1) || (getDifficultyLevel()==  DifficultyLevel.LEVEL_2 && (json.charAt(0) == "j".charAt(0)))) {
    	
    		if(json.charAt(0) == "j".charAt(0)) {
    			json = json.substring(5);
    		}
            
    		
    	  return Long.toString(polynom(value(json),powerfunction(json),multiplierfunction(json),actionfunction(json),getLength(json)));
       
       
    	}
    	
    	else if((getDifficultyLevel()==  DifficultyLevel.LEVEL_2) && (json.charAt(0) == "n".charAt(0))) {
    		 
    		 int index =0;
  	         for(int i=14; i< json.length(); i++) {
  	         	if(json.charAt(i) == "^".charAt(0)) {
  	         	  power[index] = Character.getNumericValue(json.charAt(i+1));
  	         	  index++;
  	 				
  	         	}
  	         }
           
             int l =index;
    	                
    	      
           return Long.toString(polynom(value2(json),power,multiplierfunction2(json),actionfunction2(json),l));
    	   
    	}
    	    	
    	   
    	else if(getDifficultyLevel()==  DifficultyLevel.LEVEL_3) {
    		
    		if(json.charAt(0) == "j".charAt(0)) {
    			json = json.substring(5);
    			return Long.toString(polynomLEVEL3(value(json),powerfunction(json),multiplierfunction(json),actionfunction(json),getLength(json)));
    	        
    		}
    		
    		else {
    			
    			
    			int index =0;
    	         for(int i=14; i< json.length(); i++) {
    	         	if(json.charAt(i) == "^".charAt(0)) {
    	         	  power[index] = Character.getNumericValue(json.charAt(DigitalTaxTracker.add(index, 1)));
    	           	  index = DigitalTaxTracker.add(index, 1);
    	 				
    	         	}
    	         }
             
               int l =index;
        	      
                 return Long.toString(polynomLEVEL3(value2LEVEL3(json),power,multiplierfunction2LEVEL3(json),actionfunction2LEVEL3(json),l));
        	 }
    	
    	}
    	
        return  null;
    
    }
}



