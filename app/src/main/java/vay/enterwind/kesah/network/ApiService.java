package vay.enterwind.kesah.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vay.enterwind.kesah.entities.AccessToken;
import vay.enterwind.kesah.entities.LaporanResponse;

/**
 * Created by novay on 14/07/18.
 */

public interface ApiService {

    @POST("register")
    @FormUrlEncoded
    Call<AccessToken> register(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("repeat_password") String repeat);

    @POST("login")
    @FormUrlEncoded
    Call<AccessToken> login(
            @Field("email") String email,
            @Field("password") String password);

    @POST("social")
    @FormUrlEncoded
    Call<AccessToken> socialAuth(
            @Field("name") String name,
            @Field("email") String email,
            @Field("provider") String provider,
            @Field("provider_user_id") String providerUserId);

    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(
            @Field("refresh_token") String refreshToken);

    @GET("laporan")
    Call<LaporanResponse> laporan();

}
