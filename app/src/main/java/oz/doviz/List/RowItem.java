package oz.doviz.List;

public class RowItem
{
	private String selling;
	private String update_date;
	private String buying;
	private Float change_rate;
	private String full_name;
	private String code;

	public RowItem(String selling, String update_date, String buying, Float change_rate, String full_name, String code) {
		this.selling = selling;
		this.update_date = update_date;
		this.buying = buying;
		this.change_rate = change_rate;
		this.full_name = full_name;
		this.code = code;
	}

	public String getSelling() {
		return selling;
	}

	public void setSelling(String selling) {
		this.selling = selling;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public String getBuying() {
		return buying;
	}

	public void setBuying(String buying) {
		this.buying = buying;
	}

	public Float getChange_rate() {
		return change_rate;
	}

	public void setChange_rate(Float change_rate) {
		this.change_rate = change_rate;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}