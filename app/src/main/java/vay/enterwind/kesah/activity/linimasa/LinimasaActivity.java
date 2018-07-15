package vay.enterwind.kesah.activity.linimasa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vay.enterwind.kesah.R;
import vay.enterwind.kesah.activity.BaseActivity;
import vay.enterwind.kesah.activity.auth.IntroActivity;
import vay.enterwind.kesah.library.TokenManager;

public class LinimasaActivity extends BaseActivity {

    private static final String TAG = LinimasaActivity.class.getSimpleName();
    private Context mContext = LinimasaActivity.this;
    private static final int ACTIVITY_NUM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linimasa);
        ButterKnife.bind(this);


        setupBottomNavigationView(mContext, ACTIVITY_NUM);
    }

}
