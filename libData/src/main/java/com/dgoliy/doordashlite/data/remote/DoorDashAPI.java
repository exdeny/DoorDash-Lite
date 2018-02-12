package com.dgoliy.doordashlite.data.remote;

import com.dgoliy.doordashlite.data.remote.model.Restaurant;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dgoliy on 2/11/18.
 */

public interface DoorDashAPI {
    @GET("restaurant/")
    Single<List<Restaurant>> getRestaurants(@Query("lat") Double latitude, @Query("lng") Double longitude);

    @GET("restaurant/{restId}/")
    Single<Restaurant> getRestaurantDetails(@Path("restId") Integer restaurantId);
}
