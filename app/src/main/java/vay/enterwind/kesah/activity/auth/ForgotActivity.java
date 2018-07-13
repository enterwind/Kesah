package vay.enterwind.kesah.activity.auth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vay.enterwind.kesah.R;

public class ForgotActivity extends AppCompatActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnKirim)
    LinearLayout btnKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ButterKnife.bind(this);
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
