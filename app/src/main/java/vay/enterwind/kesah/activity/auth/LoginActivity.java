package vay.enterwind.kesah.activity.auth;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.roger.catloadinglibrary.CatLoadingView;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vay.enterwind.kesah.R;
import vay.enterwind.kesah.activity.linimasa.LinimasaActivity;
import vay.enterwind.kesah.entities.AccessToken;
import vay.enterwind.kesah.entities.ApiError;
import vay.enterwind.kesah.library.FacebookManager;
import vay.enterwind.kesah.library.TokenManager;
import vay.enterwind.kesah.library.Utils;
import vay.enterwind.kesah.network.ApiService;
import vay.enterwind.kesah.network.RetrofitBuilder;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private Context mContext = LoginActivity.this;

    @BindView(R.id.scrollView) ScrollView scrollView;
    @BindView(R.id.btnBack) ImageView btnBack;
    @BindView(R.id.btnMasuk) ImageView btnMasuk;
    @BindView(R.id.btnLupa) TextView btnLupa;

    @BindView(R.id.txtPassword) EditText txtPassword;

    @BindView(R.id.inputEmail) TextInputLayout inputEmail;
    @BindView(R.id.inputSandi) TextInputLayout inputSandi;

    ApiService service;
    TokenManager tokenManager;
    AwesomeValidation validator;
    Call<AccessToken> call;
    CatLoadingView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        service = RetrofitBuilder.createService(ApiService.class);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        loading = new CatLoadingView();

        if(tokenManager.getToken().getAccessToken() != null){
            startActivity(new Intent(LoginActivity.this, LinimasaActivity.class));
            finish();
        }

        OverScrollDecoratorHelper.setUpOverScroll(scrollView);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        txtPassword.setTransformationMethod(new PasswordTransformationMethod());
    }

    @OnClick(R.id.btnBack)
    void kembali() {
        finish();
    }

    @OnClick(R.id.btnLupa)
    void lupa() {
        startActivity(new Intent(LoginActivity.this, ForgotActivity.class));
    }

    @OnClick(R.id.btnMasuk)
    void login() {

        loading.show(getSupportFragmentManager(), "");

        String email = inputEmail.getEditText().getText().toString();
        String password = inputSandi.getEditText().getText().toString();

        inputEmail.setError(null);
        inputSandi.setError(null);

        validator.clear();

        if (validator.validate()) {
            call = service.login(email, password);
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    Log.w(TAG, "onResponse: " + response);
                    if (response.isSuccessful()) {
                        tokenManager.saveToken(response.body());
                        startActivity(new Intent(LoginActivity.this, LinimasaActivity.class));
                        finish();
                    } else {
                        if (response.code() == 422) {
                            handleErrors(response.errorBody());
                        }
                        if (response.code() == 401) {
                            ApiError apiError = Utils.converErrors(response.errorBody());
                            Toast.makeText(LoginActivity.this, apiError.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                    loading.dismiss();
                }
                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Log.w(TAG, "onFailure: " + t.getMessage());
                    loading.dismiss();
                }
            });

        }

    }

    private void handleErrors(ResponseBody response) {
        ApiError apiError = Utils.converErrors(response);
        for (Map.Entry<String, List<String>> error : apiError.getErrors().entrySet()) {
            if (error.getKey().equals("email")) {
                inputEmail.setError(error.getValue().get(0));
            }
            if (error.getKey().equals("password")) {
                inputSandi.setError(error.getValue().get(0));
            }
        }
        loading.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null) {
            call.cancel();
            call = null;
        }
    }
}
