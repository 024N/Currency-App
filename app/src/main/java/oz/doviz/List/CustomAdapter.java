package oz.doviz.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import oz.doviz.R;

public class CustomAdapter extends BaseAdapter
{
	Context context;
	List<RowItem> rowItemDinings;

	List<RowItem> data;

	public ListView mylistview;

	public CustomAdapter(List<RowItem> rowItemDinings)
	{
		this.rowItemDinings = rowItemDinings;
	}

	public CustomAdapter(Context context, List<RowItem> rowItemDinings)
	{
		this.context = context;
		this.rowItemDinings = rowItemDinings;
	}

	@Override
	public int getCount()
	{
		return rowItemDinings.size();
	}

	@Override
	public Object getItem(int position)
	{
		return rowItemDinings.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return rowItemDinings.indexOf(getItem(position));
	}

	/* private view holder class */
	private class ViewHolder
	{
		TextView selling;
		TextView update_date;
		TextView buying;
		TextView change_rate;
		TextView full_name;
		TextView code;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = null;

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.list_row, null);
			holder = new ViewHolder();
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		holder.selling = (TextView) convertView.findViewById(R.id.selling);
		//holder.update_date = (TextView) convertView.findViewById(R.id.update_date);
		holder.buying = (TextView) convertView.findViewById(R.id.buying);
		//holder.change_rate = (TextView) convertView.findViewById(R.id.change_rate);
		holder.full_name = (TextView) convertView.findViewById(R.id.full_name);
		//holder.code = (TextView) convertView.findViewById(R.id.code);

		RowItem row_pos = rowItemDinings.get(position);

		holder.selling.setText(row_pos.getSelling());
		//holder.update_date.setText(row_pos.getUpdate_date());
		holder.buying.setText(row_pos.getBuying());
		//holder.change_rate.setText(row_pos.getChange_rate().toString());
		holder.full_name.setText(row_pos.getFull_name());
		//holder.code.setText(row_pos.getCode());

		return convertView;
	}

	public void setData(List<RowItem> data)
	{
		this.data = data;
		notifyDataSetChanged();
	}
}