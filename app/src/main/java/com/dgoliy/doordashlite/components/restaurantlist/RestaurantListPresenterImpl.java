package com.dgoliy.doordashlite.components.restaurantlist;

import android.location.Location;

import com.dgoliy.doordashlite.common.Assertions;
import com.dgoliy.doordashlite.common.DDLog;
import com.dgoliy.doordashlite.common.schedulers.RxScheduler;
import com.dgoliy.doordashlite.data.DataManager;
import com.dgoliy.doordashlite.data.remote.model.Restaurant;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by dgoliy on 2/11/18.
 */

public class RestaurantListPresenterImpl implements RestaurantListPresenter, OnRestaurantSelectedListener {
    private final String TAG = "RestaurantListPresenterImpl";

    private final Location HARDCODED_LOCATION = new Location("dummyprovider") {
        @Override
        public double getLongitude() {
            return -122.139956;
        }

        @Override
        public double getLatitude() {
            return 37.422740;
        }
    };

    private RxScheduler rxScheduler;
    private DataManager dataManager;

    private Disposable disposable;

    private RestaurantListView view;

    private boolean isPaused = true;

    private boolean isUpdateNeeded = false;
    private boolean updateIsLoading = false;
    private List<Restaurant> updateRestaurantListItems;

    public RestaurantListPresenterImpl(RxScheduler rxScheduler, DataManager dataManager) {
        this.rxScheduler = rxScheduler;
        this.dataManager = dataManager;
    }

    private void requestRestaurantList() {
        notifyViewStartLoading();
        disposable = dataManager.observeRestaurants(HARDCODED_LOCATION)
                .subscribeOn(rxScheduler.io())
                .observeOn(rxScheduler.main())
                .subscribe(restaurantListItems -> {
                    DDLog.d(TAG, "Restaurants loaded %d", restaurantListItems.size());
                    Assertions.assertNotNull(view);
                    if (isPaused) {
                        isUpdateNeeded = true;
                        updateIsLoading = false;
                        updateRestaurantListItems = restaurantListItems;
                    } else {
                        view.updateUI(false, restaurantListItems);
                    }
                }, throwable -> {
                    DDLog.e(TAG, "Restaurants loading exception %s", throwable);
                    Assertions.assertNotNull(view);
                    if (isPaused) {
                        isUpdateNeeded = true;
                        updateIsLoading = false;
                        updateRestaurantListItems = null;
                    } else {
                        view.updateUI(false, null);
                        view.showError(throwable.getMessage());
                    }
                });
    }

    private void updateViewIfNeeded() {
        Assertions.assertNotNull(view);
        if (isUpdateNeeded) {
            isUpdateNeeded = false;
            view.updateUI(updateIsLoading, updateRestaurantListItems);
            updateIsLoading = false;
            updateRestaurantListItems = null;
        }
    }

    private void notifyViewStartLoading() {
        Assertions.assertNotNull(view);
        if (isPaused) {
            isUpdateNeeded = true;
            updateIsLoading = true;
        } else {
            view.updateUI(true, null);
        }
    }

    @Override
    public void setup() {
        DDLog.d(TAG, "setup()");
        Assertions.assertNotNull(view);
        view.setRestaurantSelectedListener(this);
    }

    @Override
    public void refresh() {
        DDLog.d(TAG, "refresh()");
        requestRestaurantList();
    }

    @Override
    public void setView(RestaurantListView view) {
        DDLog.d(TAG, "setView(%s)", view);
        this.view = view;
    }

    @Override
    public void resume() {
        DDLog.d(TAG, "resume()");
        isPaused = false;

        if (disposable == null) {
            refresh();
        } else {
            updateViewIfNeeded();
        }
    }

    @Override
    public void pause() {
        DDLog.d(TAG, "pause()");
        isPaused = true;
    }

    @Override
    public void destroy() {
        DDLog.d(TAG, "destroy()");
        isPaused = true;
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }

    @Override
    public void onRestaurantSelected(Restaurant restaurant) {
        DDLog.d(TAG, "onRestaurantSelected(%s)", restaurant);
        Assertions.assertNotNull(view);
        view.open(restaurant);
    }
}
