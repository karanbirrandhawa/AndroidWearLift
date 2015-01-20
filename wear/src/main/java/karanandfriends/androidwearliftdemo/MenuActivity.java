package karanandfriends.androidwearliftdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MenuActivity extends Activity implements WearableListView.ClickListener{

    private TextView mTextView;
    MenuActivity ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        ctx = this;
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                // Get the list component from the layout of the activity
                WearableListView listView =
                        (WearableListView) stub.findViewById(R.id.wearable_list);


                String []elements = {"Bicep Curl", "Shoulder Flies"};

                // Assign an adapter to the list
                listView.setAdapter(new Adapter(getApplicationContext(), elements));
                listView.setClickListener(ctx);
            }
        });
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        Integer tag = (Integer) viewHolder.itemView.getTag();
        Intent intent = null;
        switch(tag.intValue()) {
            case 0:
                intent = new Intent(this, BicepCurlActivity.class);
                break;
            case 1:
                intent = new Intent(this, ShoulderFlyActivity.class);
                break;
            default:
                // Do nothing
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onTopEmptyRegionClick() {

    }

    /** Taken directly from Android Developers site **/
    private static final class Adapter extends WearableListView.Adapter {
        private String[] mDataset;
        private final Context mContext;
        private final LayoutInflater mInflater;

        // Provide a suitable constructor (depends on the kind of dataset)
        public Adapter(Context context, String[] dataset) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
            mDataset = dataset;
        }

        // Provide a reference to the type of views you're using
        public static class ItemViewHolder extends WearableListView.ViewHolder {
            private TextView textView;
            public ItemViewHolder(View itemView) {
                super(itemView);
                // find the text view within the custom item's layout
                textView = (TextView) itemView.findViewById(R.id.name);
            }
        }

        // Create new views for list items
        // (invoked by the WearableListView's layout manager)
        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
            // Inflate our custom layout for list items
            return new ItemViewHolder(mInflater.inflate(R.layout.wearable_list_item, null));
        }

        // Replace the contents of a list item
        // Instead of creating new views, the list tries to recycle existing ones
        // (invoked by the WearableListView's layout manager)
        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder,
                                     int position) {
            // retrieve the text view
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            TextView view = itemHolder.textView;
            // replace text contents
            view.setText(mDataset[position]);
            // replace list item's metadata
            holder.itemView.setTag(position);
        }

        // Return the size of your dataset
        // (invoked by the WearableListView's layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
