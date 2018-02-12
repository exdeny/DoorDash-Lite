package com.dgoliy.doordashlite.components.restaurantdetails;

import com.dgoliy.doordashlite.common.Assertions;
import com.dgoliy.doordashlite.common.DDLog;
import com.dgoliy.doordashlite.common.schedulers.RxScheduler;
import com.dgoliy.doordashlite.data.DataManager;
import com.dgoliy.doordashlite.data.remote.model.Restaurant;

import io.reactivex.disposables.Disposable;

/**
 * Created by dgoliy on 2/11/18.
 */

public class RestaurantDetailsPresenterImpl implements RestaurantDetailsPresenter {
    private final String TAG = "RestaurantListPresenter";

    private int restaurantId;

    private RxScheduler rxScheduler;
    private DataManager dataManager;

    private Disposable disposable;

    private RestaurantDetailsView view;

    private boolean isPaused = true;

    private boolean isUpdateNeeded = false;
    private boolean updateIsLoading = false;
    private Restaurant updateRestaurant;

    public RestaurantDetailsPresenterImpl(RxScheduler rxScheduler, DataManager dataManager) {
        this.rxScheduler = rxScheduler;
        this.dataManager = dataManager;
    }

    private void requestRestaurantDetails() {
        notifyViewStartLoading();
        disposable = dataManager.observeRestaurantDetails(restaurantId)
                .subscribeOn(rxScheduler.io())
                .observeOn(rxScheduler.main())
                .subscribe(restaurant -> {
                    DDLog.d(TAG, "Restaurant loaded %s", restaurant);
                    Assertions.assertNotNull(view);
                    if (isPaused) {
                        isUpdateNeeded = true;
                        updateIsLoading = false;
                        updateRestaurant = restaurant;
                    } else {
                        view.updateUI(false, restaurant);
                    }
                }, throwable -> {
                    DDLog.e(TAG, "Restaurants loading exception %s", throwable);
                    Assertions.assertNotNull(view);
                    if (isPaused) {
                        isUpdateNeeded = true;
                        updateIsLoading = false;
                        updateRestaurant = null;
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
            view.updateUI(updateIsLoading, updateRestaurant);
            updateIsLoading = false;
            updateRestaurant = null;
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
    public void setup(int restaurantId) {
        DDLog.d(TAG, "setup(%d)", restaurantId);
        this.restaurantId = restaurantId;
    }

    @Override
    public void setView(RestaurantDetailsView view) {
        DDLog.d(TAG, "setView(%s)", view);
        this.view = view;
    }

    @Override
    public void resume() {
        DDLog.d(TAG, "resume()");
        isPaused = false;

        if (disposable == null) {
            requestRestaurantDetails();
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
}
