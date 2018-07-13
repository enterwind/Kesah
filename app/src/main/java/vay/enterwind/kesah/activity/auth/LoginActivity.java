package vay.enterwind.kesah.activity.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vay.enterwind.kesah.R;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnMasuk)
    LinearLayout btnMasuk;
    @BindView(R.id.btnLupa)
    TextView btnLupa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnBack)
    void onBackClick() {
        finish();
    }

    @OnClick(R.id.btnMasuk)
    void onMasukClick() {
        Toast.makeText(this, "Login Clicked!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnLupa)
    void onLupaClick() {
        startActivity(new Intent(LoginActivity.this, ForgotActivity.class));
    }
}
