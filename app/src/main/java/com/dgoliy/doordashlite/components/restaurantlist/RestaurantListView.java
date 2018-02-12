package com.dgoliy.doordashlite.components.restaurantlist;

import com.dgoliy.doordashlite.common.BaseContract;
import com.dgoliy.doordashlite.data.remote.model.Restaurant;

import java.util.List;

/**
 * Created by dgoliy on 2/11/18.
 */

public interface RestaurantListView extends BaseContract.BaseView {
    void updateUI(boolean isLoading, List<Restaurant> list);

    void setRestaurantSelectedListener(OnRestaurantSelectedListener listener);

    void showError(String message);

    void open(Restaurant restaurantListItem);
}
