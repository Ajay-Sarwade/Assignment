package io.logistics.calculate;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DistanceService {
    
	//function to calculate distance 
	public double calculate_distance(double lat1,double lat2,double lon1,double lon2)
	{
		  lat1=Math.toRadians(lat1);
          lat2=Math.toRadians(lat2);
          lon1=Math.toRadians(lon1);
          lon2=Math.toRadians(lon2);
         
         
         // Haversine formula
         double dlon = lon2 - lon1;
         double dlat = lat2 - lat1;
         double a = Math.pow(Math.sin(dlat / 2), 2)
                  + Math.cos(lat1) * Math.cos(lat2)
                  * Math.pow(Math.sin(dlon / 2),2);
              
         double c = 2 * Math.asin(Math.sqrt(a));
  
         // Radius of earth in kilometers. Use 3956
         // for miles
         double r = 6371;
  
         // calculate the result
         c=c*r;
     
         return c;
	}
	
	//first api 
	public  double location( Location payload)
	{
		
		 double lat1=(payload.sellerLocation.latitude);
         double lat2=(payload.customerLocation.latitude);
         double lon1=(payload.sellerLocation.longitude);
         double lon2=(payload.customerLocation.longitude);

         double c=calculate_distance( lat1, lat2, lon1, lon2);
	     return c;
	}
	
	//second api
	public double pincode(Pincode payload)
	{   
		
		String seller=Integer.toString(payload.sellerPincode);
		String customer=Integer.toString(payload.customerPincode);
	
		RestTemplate restTemplate =new RestTemplate();
		LatLong[] obj1=restTemplate.getForObject("https://nominatim.openstreetmap.org/?addressdetails=1&postalcode="+seller+"&format=json", LatLong[].class);
		LatLong[] obj2=restTemplate.getForObject("https://nominatim.openstreetmap.org/?addressdetails=1&postalcode="+customer+"&format=json", LatLong[].class);
		
		double lat1= Double.parseDouble(obj1[0].lat);
		double lat2= Double.parseDouble(obj2[0].lat);
		double lon1= Double.parseDouble(obj1[0].lon);
		double lon2= Double.parseDouble(obj2[0].lon);
	    
		
		double c=calculate_distance( lat1, lat2, lon1, lon2);
		
	    return c;

	}
	
	
	
	
}






//
//{
//    "sellerLocation": {
//        "latitude": 12.31414858888888,
//        "longitude":76.6429749866288
//    },
//    "customerLocation": {
//        "latitude":12.324473270588236,
//        "longitude": 76.63131242937693
//    }
//}
















