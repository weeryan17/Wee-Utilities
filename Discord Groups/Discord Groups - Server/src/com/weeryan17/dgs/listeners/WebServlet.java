package com.weeryan17.dgs.listeners;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.HTTP;
import org.json.JSONObject;

public class WebServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    StringBuilder sb = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    try {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line).append('\n');
	        }
	    } finally {
	        reader.close();
	    }
	    System.out.println(sb.toString());
	    
	    JSONObject json = HTTP.toJSONObject(sb.toString());
	    Iterator<?> keys = json.keys();
	    
	    while(keys.hasNext()){
	    	Object key = keys.next();
	    	if(key instanceof String){
	    		Object obj = json.get(key.toString());
	    		System.out.println("Object from json: " + obj.toString());
	    	}
	    }
	}
	
}
