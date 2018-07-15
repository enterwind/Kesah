package vay.enterwind.kesah.activity.facility;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import vay.enterwind.kesah.R;
import vay.enterwind.kesah.activity.BaseActivity;

public class FacilityActivity extends BaseActivity {

    private static final String TAG = FacilityActivity.class.getSimpleName();
    private Context mContext = FacilityActivity.this;
    private static final int ACTIVITY_NUM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility);
        ButterKnife.bind(this);

        setupBottomNavigationView(mContext, ACTIVITY_NUM);
    }
}
