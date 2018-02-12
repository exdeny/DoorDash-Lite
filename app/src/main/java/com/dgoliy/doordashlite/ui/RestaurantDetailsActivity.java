package com.dgoliy.doordashlite.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dgoliy.doordashlite.DDLiteApplication;
import com.dgoliy.doordashlite.GlideRequests;
import com.dgoliy.doordashlite.R;
import com.dgoliy.doordashlite.common.DDLog;
import com.dgoliy.doordashlite.components.restaurantdetails.RestaurantDetailsPresenter;
import com.dgoliy.doordashlite.components.restaurantdetails.RestaurantDetailsView;
import com.dgoliy.doordashlite.data.remote.model.Restaurant;

import javax.inject.Inject;

public class RestaurantDetailsActivity extends AppCompatActivity implements RestaurantDetailsView {
    private final String TAG = "RestaurantDetailsActivity";

    public static final String EXTRA_RESTAURANT_ID = "EXTRA_RESTAURANT_ID";

    public static Intent createIntent(Context context, int restaurantId) {
        Intent intent = new Intent(context, RestaurantDetailsActivity.class);
        intent.putExtra(EXTRA_RESTAURANT_ID, restaurantId);
        return intent;
    }

    @Inject
    RestaurantDetailsPresenter presenter;
    @Inject
    GlideRequests glideRequests;

    private int restaurantId;

    private ImageView cover;
    private TextView title;
    private TextView status;
    private TextView foodType;
    private TextView delivery;

    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        DDLiteApplication.get().getRestaurantDetailsSubComponent().inject(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cover = findViewById(R.id.cover);
        title = findViewById(R.id.title);
        status = findViewById(R.id.status);
        foodType = findViewById(R.id.food_type);
        delivery = findViewById(R.id.delivery);

        progress = findViewById(R.id.progress);

        presenter.setView(this);
        presenter.setup(restaurantId = getIntent().getExtras().getInt(EXTRA_RESTAURANT_ID));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
        DDLiteApplication.get().releaseRestaurantDetailsSubComponent();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUI(boolean isLoading, Restaurant restaurant) {
        DDLog.d(TAG, "updateUI(%b, %s)", isLoading, restaurant);
        if (isLoading) {
            getSupportActionBar().setTitle(R.string.title_loading);
            progress.setVisibility(View.VISIBLE);
        } else if (restaurant != null) {
            getSupportActionBar().setTitle(restaurant.getName());
            progress.setVisibility(View.GONE);
            glideRequests.load(restaurant.getCoverUrl()).placeholder(R.drawable.ic_launcher_background).into(cover);
            title.setText(restaurant.getName());
            foodType.setText(restaurant.getDescription());
            status.setText(restaurant.getStatus());

            int deliveryFee = restaurant.getDeliveryFee();
            if (deliveryFee > 0) {
                delivery.setVisibility(View.VISIBLE);
                delivery.setText(UiHelper.buildDeliveryFeeString(this, deliveryFee));
            } else {
                delivery.setVisibility(View.GONE);
            }
        } else {
            getSupportActionBar().setTitle(R.string.title_no_data);
            progress.setVisibility(View.GONE);
        }
    }
}
