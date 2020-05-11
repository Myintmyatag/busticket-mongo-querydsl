package com.example.busticket_mongo.resource;

import com.example.busticket_mongo.model.BusRoute;
import com.example.busticket_mongo.repository.BusRouteRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("api/busroutes")
public class BusRouteController {

    private MongoTemplate mongoTemplate;
    private BusRouteRepository busRouteRepository;

    public BusRouteController(MongoTemplate mongoTemplate,BusRouteRepository busRouteRepository) {

        this.mongoTemplate = mongoTemplate;
        this.busRouteRepository = busRouteRepository;
    }

    @GetMapping("/all")
    public Collection<BusRoute> showAllBusRoute(){
      Collection<BusRoute> busRoutes =  this.mongoTemplate.findAll(BusRoute.class);
        return busRoutes;
    }

    @GetMapping("/{busName}")
    public Collection<BusRoute> byBusName(@PathVariable String busName){
        return this.busRouteRepository.findByBusNameContains(busName);
    }

    @GetMapping("/cityname/{toCity}")
    public Collection<BusRoute> byToCity(@PathVariable String toCity){
        return this.busRouteRepository.findByRoutesToCity(toCity);
    }

}
