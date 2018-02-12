package com.dgoliy.doordashlite.tests;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.dgoliy.doordashlite.BuildConfig;
import com.dgoliy.doordashlite.R;
import com.dgoliy.doordashlite.TestApplication;
import com.dgoliy.doordashlite.data.RestaurantHelper;
import com.dgoliy.doordashlite.data.remote.DoorDashAPI;
import com.dgoliy.doordashlite.data.remote.model.Restaurant;
import com.dgoliy.doordashlite.ui.MainActivity;
import com.dgoliy.doordashlite.ui.RestaurantDetailsActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowIntent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static com.dgoliy.doordashlite.ui.RestaurantDetailsActivity.EXTRA_RESTAURANT_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by dgoliy on 2/11/18.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TestApplication.class)
public class MainActivityTest {
    private int COUNT_MOCKED_RESTAURANTS = 4;

    private RecyclerView restaurantRecyclerView;
    private ShadowActivity mainActivityShadow;

    private DoorDashAPI mockApi() {
        DoorDashAPI api = mock(DoorDashAPI.class);

        List<Restaurant> mockedResult = new ArrayList<>();
        for (int i = 0; i < COUNT_MOCKED_RESTAURANTS; i++) {
            mockedResult.add(RestaurantHelper.create(
                    i, "t" + i, "d" + i, "url", "open", i
            ));
        }

        when(api.getRestaurants(anyDouble(), anyDouble()))
                .thenReturn(Single.just(mockedResult));

        return api;
    }

    @Before
    public void setup() {
        ((TestApplication) ShadowApplication.getInstance().getApplicationContext())
                .getTestDataModule().setApi(mockApi());

        ActivityController<MainActivity> activityController = Robolectric.buildActivity(MainActivity.class);
        activityController.create().start().resume().visible();

        MainActivity activity = activityController.get();
        mainActivityShadow = shadowOf(activity);
        restaurantRecyclerView = ((RecyclerView) mainActivityShadow.findViewById(R.id.list));

        assertNotNull(restaurantRecyclerView);
    }

    @Test
    public void countOfRestaurantsInTheList_isCorrect() {
        assertEquals(COUNT_MOCKED_RESTAURANTS, restaurantRecyclerView.getAdapter().getItemCount());
    }

    @Test
    public void restaurantDetailsActivityWhenItemIsClicked_shouldStart() {
        restaurantRecyclerView.getChildAt(0).performClick();

        Intent startedIntent = mainActivityShadow.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(RestaurantDetailsActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void restaurantIdPassedToDetailsActivityOnClick_isCorrect() {
        restaurantRecyclerView.getChildAt(1).performClick();

        Intent startedIntent = mainActivityShadow.getNextStartedActivity();
        assertEquals(1, startedIntent.getIntExtra(EXTRA_RESTAURANT_ID, -1));
    }
}
