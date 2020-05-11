package com.example.busticket_mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "busroute")
public class BusRoute {

    @Id
    private String Id;
    private Bus bus;

    @Indexed(direction = IndexDirection.ASCENDING)
    private Routes routes;

    private LocalDateTime depatureTime;
    private LocalDateTime arrivalTime;

    @Field("busstops")
    private List<String> busStops;

    public BusRoute(Bus bus, Routes routes, LocalDateTime depatureTime, LocalDateTime arrivalTime, List<String> busStops) {
        this.bus = bus;
        this.routes = routes;
        this.depatureTime = depatureTime;
        this.arrivalTime = arrivalTime;
        this.busStops = busStops;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Routes getRoutes() {
        return routes;
    }

    public void setRoutes(Routes routes) {
        this.routes = routes;
    }

    public LocalDateTime getDepatureTime() {
        return depatureTime;
    }

    public void setDepatureTime(LocalDateTime depatureTime) {
        this.depatureTime = depatureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<String> getBusStops() {
        return busStops;
    }

    public void setBusStops(List<String> busStops) {
        this.busStops = busStops;
    }
}
