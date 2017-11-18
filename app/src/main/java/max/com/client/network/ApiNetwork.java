package max.com.client.network;

import java.util.List;

import max.com.client.model.Message;

import max.com.client.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Maxim on 9/27/2017.
 */

public interface ApiNetwork {

    @POST("register/")
    @FormUrlEncoded
    Call<User> register(@Field("phone") String phone, @Field("password") String password);


    @POST("login")
    @FormUrlEncoded
    Call<User> login(@Field("phone") String phone, @Field("password") String password);

    @POST("createMessage")
    @FormUrlEncoded
    Call<Message> createMessage(@Field("userPhone") String userPhone,
                                @Field("senderPhone") String senderPhone,
                                @Field("message") String message);

    @GET("findMessage")
    Call<List<Message>> findMessage(@Query("userPhone") String userPhone, @Query("senderPhone") String senderPhone);

}
