package admin.ats.com.focaloidproject;

import admin.ats.com.focaloidproject.Model.JSONResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Anju on 25-04-2017.
 */

public interface ProductAPI {
    @GET("services/get_products")
    Call<JSONResponse>getJSON();
}
