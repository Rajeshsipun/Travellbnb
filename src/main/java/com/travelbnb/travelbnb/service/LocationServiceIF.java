package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.entity.Location;

public interface LocationServiceIF {
    Location addLocation(Location locationEntity);

    void deleteLocetion(long locationId);

    Location updateLocation(long locationId, Location location);
}
