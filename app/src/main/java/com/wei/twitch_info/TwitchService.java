package com.wei.twitch_info;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by wei on 2018/2/27.
 */

public interface TwitchService {
    @GET("{user}?client_id=m2qy6mrlroi6hiv49nkowzv9pshk80")
    Call<TwitchInfo> getInfo(@Path("user") String user);
}
