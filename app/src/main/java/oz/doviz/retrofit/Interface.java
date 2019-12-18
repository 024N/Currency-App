package oz.doviz.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface
{
    @GET("api/v1/currencies/all/latest")
    Call<Model[]> getJsonValues();
}
