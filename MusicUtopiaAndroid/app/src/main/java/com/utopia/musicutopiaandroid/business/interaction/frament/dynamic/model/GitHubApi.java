package com.utopia.musicutopiaandroid.business.interaction.frament.dynamic.model;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/4/24/024.
 */

public interface GitHubApi {
    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributorsBySimpleGetCall(@Path("owner")String owner,@Path("repo") String repo);

}
