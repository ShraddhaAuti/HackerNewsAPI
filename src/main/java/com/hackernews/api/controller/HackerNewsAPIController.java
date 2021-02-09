package com.hackernews.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hackernews.api.serviceimpl.HackerNewsServiceImpl;

@RestController
public class HackerNewsAPIController {


	public static String APPLICATION_JSON="application/json";
	
	@Autowired
	HackerNewsServiceImpl  hackernewsservice;
 
	 @RequestMapping("/best-stories")
	   public Object getBestStories() throws Exception {
		 	
	      try {
	    	  
	    	  ResponseEntity<Object> response  = hackernewsservice.getBestStories();
	    	 if (response == null) {
	    	            throw new Exception("Server responded with no data");
	    	 }
	    	 
	          return response;
	      } catch (Exception e) {
	    
	    	   e.printStackTrace();
	    	   
	      }
	      return null;
	   }
	  
	 @RequestMapping("/paststories")
	   public Object getTopPastStories() throws Exception {  
		 	
	      try {
	    	  
	    	  ResponseEntity<Object> response  = hackernewsservice.getTopPastStories();
	    	 if (response == null) {
	    	            throw new Exception("Server responded with no data");
	    	 }
	    	 
	          return response;
	      } catch (Exception e) {
	    
	    	   e.printStackTrace();
	    	   
	      }
	      return null;
	   }
	 
	 @GetMapping("/comments/{id}")
	   public Object getTopComments(@PathVariable("id") int id) throws Exception { 
		 	
	      try {
	    	  
	    	  ResponseEntity<Object> response  = hackernewsservice.getTopComments(id);
	    	 if (response == null) {
	    	            throw new Exception("Server responded with no data");
	    	 }
	    	 
	          return response;
	      } catch (Exception e) {
	    
	    	   e.printStackTrace();
	    	   
	      }
	      return null;
	   }

}
