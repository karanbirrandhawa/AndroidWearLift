package karanandfriends.androidwearliftdemo.resources;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import karanandfriends.androidwearliftdemo.R;

/**
 * Taken more or less directly from Android developers resource
 */
public class CoreWearableListItemLayout extends LinearLayout {

    private TextView mName;

    private final float mFadedTextAlpha;
    private final int mFadedCircleColor;
    private final int mChosenCircleColor;

    public CoreWearableListItemLayout(Context context) {
        this(context, null);
    }

    public CoreWearableListItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoreWearableListItemLayout(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);

        mFadedTextAlpha = 50.0f / 100f;
        mFadedCircleColor = getResources().getColor(R.color.grey);
        mChosenCircleColor = getResources().getColor(R.color.blue);
    }

    // Get references to the icon and text in the item layout definition
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // These are defined in the layout file for list items
        // (see next section)
        mName = (TextView) findViewById(R.id.name);
    }

    public void onCenterPosition(boolean animate) {
        mName.setAlpha(1f);
    }

    public void onNonCenterPosition(boolean animate) {
        mName.setAlpha(mFadedTextAlpha);
    }

}
