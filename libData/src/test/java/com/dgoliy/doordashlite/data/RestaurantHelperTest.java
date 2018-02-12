package com.dgoliy.doordashlite.data;

import com.dgoliy.doordashlite.data.remote.model.Restaurant;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by dgoliy on 2/11/18.
 */

public class RestaurantHelperTest {

    @Test
    public void create_isCorrect() throws Exception {
        int id = 123;
        String name = "hello";
        String description = "world";
        String coverUrl = "1234";
        String status = "+++";
        int deliveryFee = 15;
        final Restaurant restaurant = RestaurantHelper.create(
                id,
                name,
                description,
                coverUrl,
                status,
                deliveryFee
        );

        assertEquals(id, restaurant.getId());
        assertEquals(name, restaurant.getName());
        assertEquals(description, restaurant.getDescription());
        assertEquals(coverUrl, restaurant.getCoverUrl());
        assertEquals(status, restaurant.getStatus());
        assertEquals(deliveryFee, restaurant.getDeliveryFee());
    }

    @Test
    public void copy_isCorrect() throws Exception {
        final Restaurant srcRestaurant = RestaurantHelper.create(
                123,
                "hello",
                "world",
                "111122223333",
                "st",
                999
        );
        final Restaurant dstRestaurant = new Restaurant();

        RestaurantHelper.copy(srcRestaurant, dstRestaurant);

        assertEquals(srcRestaurant.getId(), dstRestaurant.getId());
        assertEquals(srcRestaurant.getName(), dstRestaurant.getName());
        assertEquals(srcRestaurant.getDescription(), dstRestaurant.getDescription());
        assertEquals(srcRestaurant.getCoverUrl(), dstRestaurant.getCoverUrl());
        assertEquals(srcRestaurant.getStatus(), dstRestaurant.getStatus());
        assertEquals(srcRestaurant.getDeliveryFee(), dstRestaurant.getDeliveryFee());
    }
}