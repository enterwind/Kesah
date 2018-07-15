package vay.enterwind.kesah.activity.report;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import vay.enterwind.kesah.R;
import vay.enterwind.kesah.activity.BaseActivity;

public class ReportActivity extends BaseActivity {

    private static final String TAG = ReportActivity.class.getSimpleName();
    private Context mContext = ReportActivity.this;
    private static final int ACTIVITY_NUM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        setupBottomNavigationView(mContext, ACTIVITY_NUM);
    }
}
