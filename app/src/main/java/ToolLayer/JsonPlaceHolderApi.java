package ToolLayer;

import java.util.List;

import EntityLayer.Oduh.BalOrmani;
import EntityLayer.SendParametersForServer;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @POST("OdunDisiRS/oduhDepoBalOrmaniService")
    Call<List<BalOrmani>>  getBalOrmaniService(@Body SendParametersForServer parameter);

}
