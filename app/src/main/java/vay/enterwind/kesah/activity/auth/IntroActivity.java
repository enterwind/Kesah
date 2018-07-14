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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vay.enterwind.kesah.R;
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

    private static final int ANIM_DURATION_TOOLBAR = 300;
    @BindView(R.id.linearLayout) LinearLayout linearLayout;
    private static final Interpolator INTERPOLATOR = new AccelerateInterpolator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);


    }

//    private void startIntroAnimation() {
//
//        linearLayout.getHeight();
//        btnRegistrasi.setTranslationY(100);
//        btnMasuk.setTranslationY(100);
//
//        btnRegistrasi.animate()
//                .translationY(0)
//                .setDuration(ANIM_DURATION_TOOLBAR)
//                .setStartDelay(300);
//        btnMasuk.animate()
//                .translationY(0)
//                .setDuration(ANIM_DURATION_TOOLBAR)
//                .setStartDelay(400);
//
//        btnFacebook.setScaleX(0);
//        btnFacebook.setScaleY(0);
//        btnFacebook.animate()
//                .scaleY(1)
//                .scaleX(1)
//                .setDuration(250)
//                .setInterpolator(INTERPOLATOR)
//                .setStartDelay(500)
//                .start();
//
//        btnGoogle.setScaleX(0);
//        btnGoogle.setScaleY(0);
//        btnGoogle.animate()
//                .scaleY(1)
//                .scaleX(1)
//                .setDuration(200)
//                .setInterpolator(INTERPOLATOR)
//                .setStartDelay(700)
//                .start();
//    }

    @OnClick(R.id.btnFacebook)
    void onFacebookClick() {
        Toast.makeText(mContext, "Facebook Click!", Toast.LENGTH_SHORT).show();
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

}
