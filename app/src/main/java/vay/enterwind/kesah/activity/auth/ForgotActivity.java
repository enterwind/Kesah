package vay.enterwind.kesah.activity.auth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import vay.enterwind.kesah.R;

public class ForgotActivity extends AppCompatActivity {

    @BindView(R.id.scrollView) ScrollView scrollView;
    @BindView(R.id.btnBack) ImageView btnBack;
    @BindView(R.id.btnKirim) ImageView btnKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ButterKnife.bind(this);

        OverScrollDecoratorHelper.setUpOverScroll(scrollView);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @OnClick(R.id.btnBack)
    void onBackClick() {
        finish();
    }

    @OnClick(R.id.btnKirim)
    void onKirimClick() {
        Toast.makeText(this, "Kirim Clicked!", Toast.LENGTH_SHORT).show();
    }

}
