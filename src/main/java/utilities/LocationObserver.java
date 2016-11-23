/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;
import java.util.List;
import websockets.MockLocationService;

/**
 *
 * @author karin757
 */
public class LocationObserver {
    public static final LocationObserver observer = new LocationObserver();
    public List<MockLocationService> list = new ArrayList<>();
    
    private LocationObserver(){}
    
    public void add(MockLocationService service){
        list.add(service);
    }
    
    public void fire(double latitude, double longitude){
        for(MockLocationService service : list){
            service.sendLocation(latitude, longitude);
        }
    }
}
