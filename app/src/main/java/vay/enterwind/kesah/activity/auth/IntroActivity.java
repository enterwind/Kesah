package vay.enterwind.kesah.activity.auth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vay.enterwind.kesah.R;

public class IntroActivity extends AppCompatActivity {

    private static final String TAG = IntroActivity.class.getSimpleName();
    private Context mContext = IntroActivity.this;
    boolean doubleBackToExitPressedOnce = false;

    @BindView(R.id.btnFacebook)
    LinearLayout btnFacebook;
    @BindView(R.id.btnGoogle)
    LinearLayout btnGoogle;
    @BindView(R.id.btnRegistrasi)
    TextView btnRegistrasi;
    @BindView(R.id.btnMasuk)
    TextView btnMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnFacebook)
    void onFacebookClick() {
        Toast.makeText(mContext, "Facebook Click!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnGoogle)
    void onGoogleClick() {
        Toast.makeText(mContext, "Google Click!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnRegistrasi)
    void onRegistrasiClick() {
        startActivity(new Intent(IntroActivity.this, RegisterActivity.class));
    }

    @OnClick(R.id.btnMasuk)
    void onMasukClick() {
        startActivity(new Intent(IntroActivity.this, LoginActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            if (doubleBackToExitPressedOnce) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    finishAffinity();
                }
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    }

}
