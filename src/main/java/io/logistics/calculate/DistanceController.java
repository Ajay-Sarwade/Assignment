package io.logistics.calculate;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class DistanceController {
	
	@Autowired
	private DistanceService distanceservice;
	
	//First Api to calculate cost by latitude and longitude
	@RequestMapping(method=RequestMethod.GET,value="/location")
	public int  calculate1(@RequestBody Location payload ) 
	{
		 double cost=distanceservice.location(payload);
		 return (int)cost;
	}
	
    //Second Api to calculate cost by pincode (by calling another api)
	@RequestMapping(method=RequestMethod.GET,value="/pincode")
	public int calculate2(@RequestBody Pincode payload)
	{
		double cost=distanceservice.pincode(payload);
		return (int)cost;
	}
}
