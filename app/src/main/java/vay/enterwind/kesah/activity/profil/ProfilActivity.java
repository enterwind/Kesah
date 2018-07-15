package vay.enterwind.kesah.activity.profil;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import vay.enterwind.kesah.R;
import vay.enterwind.kesah.activity.BaseActivity;

public class ProfilActivity extends BaseActivity {

    private static final String TAG = ProfilActivity.class.getSimpleName();
    private Context mContext = ProfilActivity.this;
    private static final int ACTIVITY_NUM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        ButterKnife.bind(this);

        setupBottomNavigationView(mContext, ACTIVITY_NUM);
    }
}
