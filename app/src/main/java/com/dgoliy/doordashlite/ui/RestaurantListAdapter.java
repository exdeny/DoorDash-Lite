package com.dgoliy.doordashlite.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgoliy.doordashlite.DDLiteApplication;
import com.dgoliy.doordashlite.GlideRequests;
import com.dgoliy.doordashlite.R;
import com.dgoliy.doordashlite.components.restaurantlist.OnRestaurantSelectedListener;
import com.dgoliy.doordashlite.data.remote.model.Restaurant;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by dgoliy on 2/11/18.
 */

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantListViewHolder> {
    private List<Restaurant> list;

    private OnRestaurantSelectedListener listener;

    @Inject
    GlideRequests glideRequests;

    public RestaurantListAdapter() {
        DDLiteApplication.get().getAppComponent().inject(this);
    }

    public void setSelectedListener(OnRestaurantSelectedListener listener) {
        this.listener = listener;
    }

    public void swap(List<Restaurant> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RestaurantListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_restaurant_item, parent, false);
        return new RestaurantListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RestaurantListViewHolder holder, int position) {
        holder.apply(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class RestaurantListViewHolder extends RecyclerView.ViewHolder {
        private ImageView cover;
        private TextView title;
        private TextView foodType;
        private TextView status;

        public RestaurantListViewHolder(View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.cover);
            title = itemView.findViewById(R.id.title);
            foodType = itemView.findViewById(R.id.food_type);
            status = itemView.findViewById(R.id.status);
        }

        public void apply(final Restaurant item) {
            glideRequests.load(item.getCoverUrl()).into(cover);
            title.setText(item.getName());
            foodType.setText(item.getDescription());
            status.setText(item.getStatus());

            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onRestaurantSelected(item);
                }
            });
        }
    }
}
