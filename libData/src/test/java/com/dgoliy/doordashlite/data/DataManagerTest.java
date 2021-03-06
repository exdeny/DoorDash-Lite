package com.dgoliy.doordashlite.data;

import android.location.Location;

import com.dgoliy.doordashlite.data.remote.DoorDashAPI;
import com.dgoliy.doordashlite.data.remote.model.Restaurant;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dgoliy on 2/11/18.
 */

public class DataManagerTest {
    private DoorDashAPI api;
    private DataManager dataManager;

    @Before
    public void setup() {
        api = mock(DoorDashAPI.class);
        dataManager = new DataManager(api);
    }

    @Test
    public void countReturned_isCorrect() {
        final int mockedRestaurantsCount = 11;
        List<Restaurant> mockedResult = new ArrayList<>();
        for (int i = 0; i < mockedRestaurantsCount; i++) {
            mockedResult.add(RestaurantHelper.create(
                    i, "t" + i, "d" + i, "url", "open", i
            ));
        }

        when(api.getRestaurants(anyDouble(), anyDouble()))
                .thenReturn(Single.just(mockedResult));

        List<Restaurant> returnedList = dataManager
                .observeRestaurants(new Location("dummy")).blockingGet();

        assertEquals(mockedRestaurantsCount, returnedList.size());
    }

    @Test
    public void restaurantReturned_isCorrect() {
        final Restaurant mockedRestaurant = RestaurantHelper.create(
                111,
                "123456",
                "qwerty",
                "url_http",
                "no status",
                123456
        );

        when(api.getRestaurantDetails(111))
                .thenReturn(Single.just(mockedRestaurant));

        Restaurant returnedRestaurant = dataManager
                .observeRestaurantDetails(111).blockingGet();

        assertEquals(mockedRestaurant.getId(), returnedRestaurant.getId());
        assertEquals(mockedRestaurant.getName(), returnedRestaurant.getName());
        assertEquals(mockedRestaurant.getDescription(), returnedRestaurant.getDescription());
        assertEquals(mockedRestaurant.getCoverUrl(), returnedRestaurant.getCoverUrl());
        assertEquals(mockedRestaurant.getStatus(), returnedRestaurant.getStatus());
        assertEquals(mockedRestaurant.getDeliveryFee(), returnedRestaurant.getDeliveryFee());
    }
}