package vay.enterwind.kesah.activity.auth;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.roger.catloadinglibrary.CatLoadingView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import vay.enterwind.kesah.R;
import vay.enterwind.kesah.activity.linimasa.LinimasaActivity;
import vay.enterwind.kesah.entities.AccessToken;
import vay.enterwind.kesah.library.FacebookManager;
import vay.enterwind.kesah.library.TokenManager;
import vay.enterwind.kesah.network.ApiService;
import vay.enterwind.kesah.network.RetrofitBuilder;
import vay.enterwind.kesah.tested.likeanimation.LikeAnimationActivity;
import vay.enterwind.kesah.utils.Animation;

public class IntroActivity extends AppCompatActivity {

    private static final String TAG = IntroActivity.class.getSimpleName();
    private Context mContext = IntroActivity.this;
    boolean doubleBackToExitPressedOnce = false;

    @BindView(R.id.btnFacebook) LinearLayout btnFacebook;
    @BindView(R.id.btnGoogle) LinearLayout btnGoogle;
    @BindView(R.id.btnRegistrasi) TextView btnRegistrasi;
    @BindView(R.id.btnMasuk) TextView btnMasuk;

    @BindView(R.id.linearLayout) LinearLayout linearLayout;

    ApiService service;
    TokenManager tokenManager;
    Call<AccessToken> call;
    FacebookManager facebookManager;
    CatLoadingView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);

        loading = new CatLoadingView();
        service = RetrofitBuilder.createService(ApiService.class);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        facebookManager = new FacebookManager(service, tokenManager);

        if(tokenManager.getToken().getAccessToken() != null){
            startActivity(new Intent(IntroActivity.this, LinimasaActivity.class));
            finish();
        }
    }

    @OnClick(R.id.btnFacebook)
    void onFacebookClick() {
        loading.show(getSupportFragmentManager(), "");

        facebookManager.login(this, new FacebookManager.FacebookLoginListener() {
            @Override
            public void onSuccess() {
                facebookManager.clearSession();
                startActivity(new Intent(IntroActivity.this, LinimasaActivity.class));
                finish();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(IntroActivity.this, message, Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }

    @OnClick(R.id.btnGoogle)
    void onGoogleClick() {
        startActivity(new Intent(IntroActivity.this, LikeAnimationActivity.class));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null) {
            call.cancel();
            call = null;
        }
        facebookManager.onDestroy();
    }

}
