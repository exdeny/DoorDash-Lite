package com.dgoliy.doordashlite.data;

import com.dgoliy.doordashlite.data.remote.model.Restaurant;

/**
 * Created by dgoliy on 2/11/18.
 */

public class RestaurantHelper {
    public static Restaurant create(int id, String name, String description, String coverUrl, String status, int deliveryFee) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);
        restaurant.setName(name);
        restaurant.setDescription(description);
        restaurant.setCoverUrl(coverUrl);
        restaurant.setStatus(status);
        restaurant.setDeliveryFee(deliveryFee);
        return restaurant;
    }

    public static void copy(Restaurant restaurantSrc, Restaurant restaurantDst) {
        restaurantDst.setId(restaurantSrc.getId());
        restaurantDst.setName(restaurantSrc.getName());
        restaurantDst.setDescription(restaurantSrc.getDescription());
        restaurantDst.setCoverUrl(restaurantSrc.getCoverUrl());
        restaurantDst.setStatus(restaurantSrc.getStatus());
        restaurantDst.setDeliveryFee(restaurantSrc.getDeliveryFee());
    }
}
