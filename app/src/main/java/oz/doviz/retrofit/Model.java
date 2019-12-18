package oz.doviz.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("selling")
    @Expose
    public String selling;
    @SerializedName("update_date")
    @Expose
    public String updateDate;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("buying")
    @Expose
    public String buying;
    @SerializedName("change_rate")
    @Expose
    public Float changeRate;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("full_name")
    @Expose
    public String fullName;
    @SerializedName("code")
    @Expose
    public String code;

}