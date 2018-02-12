package com.dgoliy.doordashlite.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dgoliy.doordashlite.DDLiteApplication;
import com.dgoliy.doordashlite.R;
import com.dgoliy.doordashlite.common.DDLog;
import com.dgoliy.doordashlite.components.restaurantlist.OnRestaurantSelectedListener;
import com.dgoliy.doordashlite.components.restaurantlist.RestaurantListPresenter;
import com.dgoliy.doordashlite.components.restaurantlist.RestaurantListView;
import com.dgoliy.doordashlite.data.remote.model.Restaurant;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements RestaurantListView {
    private final String TAG = "MainActivity";

    @Inject
    RestaurantListPresenter presenter;

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    private RestaurantListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DDLiteApplication.get().getAppComponent().inject(this);

        getSupportActionBar().setTitle(R.string.title_discover);

        refreshLayout = findViewById(R.id.refresh);
        recyclerView = findViewById(R.id.list);

        refreshLayout.setOnRefreshListener(() -> presenter.refresh());

        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        recyclerView.setAdapter(adapter = new RestaurantListAdapter());

        presenter.setView(this);
        presenter.setup();
    }

    @Override
    public void onResume() {
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
    }

    @Override
    public void updateUI(boolean isLoading, List<Restaurant> list) {
        DDLog.d(TAG, "updateUI(%b, %s)", isLoading, list);
        refreshLayout.setRefreshing(isLoading);
        adapter.swap(list);
    }

    @Override
    public void setRestaurantSelectedListener(OnRestaurantSelectedListener listener) {
        adapter.setSelectedListener(listener);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void open(Restaurant restaurantListItem) {
        DDLog.d(TAG, "open(%s)", restaurantListItem);
        startActivity(RestaurantDetailsActivity.createIntent(this, restaurantListItem.getId()));
    }
}
