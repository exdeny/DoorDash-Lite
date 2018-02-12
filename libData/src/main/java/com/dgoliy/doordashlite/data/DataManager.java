package com.dgoliy.doordashlite.data;

import android.location.Location;

import com.dgoliy.doordashlite.data.remote.DoorDashAPI;
import com.dgoliy.doordashlite.data.remote.model.Restaurant;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by dgoliy on 2/11/18.
 */
// COMMENT:
// I use this abstraction keeping in mind caching as the next step.
// This way I will encapsulate fetching and caching under the hood of DataManager.
public class DataManager {
    private DoorDashAPI api;

    public DataManager(DoorDashAPI api) {
        this.api = api;
    }

    public Single<List<Restaurant>> observeRestaurants(Location location) {
        return api.getRestaurants(location.getLatitude(), location.getLongitude());
    }

    public Single<Restaurant> observeRestaurantDetails(Integer restaurantId) {
        return api.getRestaurantDetails(restaurantId);
    }
}
