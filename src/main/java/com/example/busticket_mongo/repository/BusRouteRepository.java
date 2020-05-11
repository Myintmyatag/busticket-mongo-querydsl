package com.example.busticket_mongo.repository;

import com.example.busticket_mongo.model.BusRoute;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRouteRepository extends MongoRepository<BusRoute,String> ,
        QuerydslPredicateExecutor<BusRoute> {

    List<BusRoute> findByBusNameContains(String busName);

    List<BusRoute> findByRoutesToCity(String toCity);
}
