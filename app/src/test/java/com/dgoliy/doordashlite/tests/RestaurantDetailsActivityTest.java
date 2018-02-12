package com.dgoliy.doordashlite.tests;

import android.widget.ImageView;
import android.widget.TextView;

import com.dgoliy.doordashlite.BuildConfig;
import com.dgoliy.doordashlite.R;
import com.dgoliy.doordashlite.TestApplication;
import com.dgoliy.doordashlite.data.RestaurantHelper;
import com.dgoliy.doordashlite.data.remote.DoorDashAPI;
import com.dgoliy.doordashlite.data.remote.model.Restaurant;
import com.dgoliy.doordashlite.ui.RestaurantDetailsActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import io.reactivex.Single;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dgoliy on 2/11/18.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TestApplication.class)
public class RestaurantDetailsActivityTest {
    private Restaurant mockedRestaurant;

    private ImageView cover;
    private TextView title;
    private TextView status;
    private TextView foodType;

    private DoorDashAPI mockApi() {
        DoorDashAPI api = mock(DoorDashAPI.class);

        mockedRestaurant = RestaurantHelper.create(
                123,
                "hello",
                "world",
                "111122223333",
                "st",
                999
        );

        when(api.getRestaurantDetails(mockedRestaurant.getId()))
                .thenReturn(Single.just(mockedRestaurant));

        return api;
    }

    @Before
    public void setup() {
        ((TestApplication) ShadowApplication.getInstance().getApplicationContext())
                .getTestDataModule().setApi(mockApi());

        ActivityController<RestaurantDetailsActivity> activityController = Robolectric
                .buildActivity(
                        RestaurantDetailsActivity.class,
                        RestaurantDetailsActivity.createIntent(RuntimeEnvironment.application, mockedRestaurant.getId()));
        activityController.create().start().resume().visible();

        RestaurantDetailsActivity activity = activityController.get();

        cover = activity.findViewById(R.id.cover);
        title = activity.findViewById(R.id.title);
        status = activity.findViewById(R.id.status);
        foodType = activity.findViewById(R.id.food_type);
    }

    @Test
    public void allViewsAreInPlace() {
        assertNotNull(cover);
        assertNotNull(title);
        assertNotNull(status);
        assertNotNull(foodType);
    }

    @Test
    public void coverSrc_isCorrect() {
        assertNotNull(cover.getDrawable());
    }

    @Test
    public void title_isCorrect() {
        assertEquals(mockedRestaurant.getName(), title.getText().toString());
    }

    @Test
    public void status_isCorrect() {
        assertEquals(mockedRestaurant.getStatus(), status.getText().toString());
    }

    @Test
    public void foodType_isCorrect() {
        assertEquals(mockedRestaurant.getDescription(), foodType.getText().toString());
    }
}
