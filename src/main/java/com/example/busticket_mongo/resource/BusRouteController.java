package com.example.busticket_mongo.resource;

import com.example.busticket_mongo.model.BusRoute;
import com.example.busticket_mongo.model.BusStatus;
import com.example.busticket_mongo.model.QBusRoute;
import com.example.busticket_mongo.model.QRoutes;
import com.example.busticket_mongo.repository.BusRouteRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
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

    @GetMapping("/query/{busName}/{toCity}")
    public Collection<BusRoute> byBusNameAndToCItyAndStatus(@PathVariable String busName,@PathVariable String toCity){
        QBusRoute qBusRoute = new QBusRoute("query");
        BooleanExpression busNamePredicate = qBusRoute.bus.name.eq(busName);
         Predicate cityPredicate = qBusRoute.routes.toCity.eq(toCity);
         Predicate busStatusPredicate = qBusRoute.bus.busStatus.eq(BusStatus.MODERATE);
         Predicate customPredicate = busNamePredicate.and(cityPredicate).and(busStatusPredicate);

         return (Collection<BusRoute>) this.busRouteRepository.findAll(customPredicate);
    }


}
