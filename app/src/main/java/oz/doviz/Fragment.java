package oz.doviz;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import oz.doviz.List.CustomAdapter;
import oz.doviz.List.RowItem;
import oz.doviz.retrofit.Interface;
import oz.doviz.retrofit.Model;
import oz.doviz.retrofit.RetroClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment extends android.app.Fragment
{
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Model> models = new ArrayList<>();

    public ArrayList<String> selling = new ArrayList<String>();
    public ArrayList<String> updateDate = new ArrayList<String>();
    public ArrayList<String> buying = new ArrayList<String>();
    public ArrayList<Float> changeRate = new ArrayList<Float>();
    public ArrayList<String> fullName = new ArrayList<String>();
    public ArrayList<String> code = new ArrayList<String>();

    public List<RowItem> rowItems;
    public ListView mylistview;

    int count;
    public View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment, container, false);

        getValues();

        if(isNetworkAvailable())
        {
            count = 0;
            Timer timer = new Timer();
            Fragment.MyTimer mt = new Fragment.MyTimer();
            timer.schedule(mt, 30000, 30000);
        }
        else
            Toast.makeText(getActivity(), "İnternet Bağlantınızı Kontrol Edin!", Toast.LENGTH_LONG).show();

        return view;
    }

    public void getValues()
    {
        selling.clear();
        updateDate.clear();
        buying.clear();
        changeRate.clear();
        fullName.clear();
        code.clear();
        models.clear();

        rowItems = new ArrayList<RowItem>();
        rowItems.clear();

        Interface diningInterface;
        diningInterface = null;

        diningInterface = RetroClient.getClient().create(Interface.class);
        Call<Model[]> call;
        call = null;
        call = diningInterface.getJsonValues();

        mylistview = (ListView) view.findViewById(R.id.fragment_list);
        final CustomAdapter adapter;
        adapter = new CustomAdapter(getActivity(), rowItems);
        mylistview.setAdapter(adapter);
        call.enqueue(new Callback<Model[]>()
        {
            @Override
            public void onResponse(Call<Model[]> call, Response<Model[]> response)
            {
                models = new LinkedList<>(Arrays.asList(response.body()));

                //cacheVersion = models.get(0).cacheVersion;
                for(int i = 0; i< models.size(); i++)
                {
                    if(models.get(i).selling.length()>5)
                        selling.add(models.get(i).selling.substring(0,6));
                    else
                        selling.add(models.get(i).selling);

                    if(models.get(i).buying.length()>5)
                        buying.add(models.get(i).buying.substring(0,6));
                    else
                        buying.add(models.get(i).buying);

                    updateDate.add(models.get(i).updateDate);

                    changeRate.add(models.get(i).changeRate);
                    fullName.add(models.get(i).fullName+"("+models.get(i).code+")");
                    code.add(models.get(i).code);

                    RowItem item = new RowItem(selling.get(i),  updateDate.get(i),  buying.get(i),  changeRate.get(i),  fullName.get(i),  code.get(i));
                    rowItems.add(item);
                }
                //mylistview.smoothScrollToPosition(7);

                adapter.setData(rowItems);
            }
            @Override
            public void onFailure(Call<Model[]> call, Throwable t)
            {
                Log.e(TAG, "Error: " + t.toString());
            }
        });
    }

    class MyTimer extends TimerTask
    {
        public void run()
        {
            if(this == null)
                return;

            getActivity().runOnUiThread(new Runnable()
            {
                public void run()
                {
                    getValues();

                    count++;

                    if(count == 60)
                    {
                        PackageManager packageManager = getActivity().getPackageManager();
                        Intent intent = packageManager.getLaunchIntentForPackage(getActivity().getPackageName());
                        ComponentName componentName = intent.getComponent();
                        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
                        getActivity().startActivity(mainIntent);
                        Runtime.getRuntime().exit(0);
                    }

                    if(!isNetworkAvailable())
                    {
                        Toast.makeText(getActivity(), "İnternet Bağlantınızı Kontrol Edin!", Toast.LENGTH_LONG).show();
                    }
                    //cancel();
                }
            });
        }
    }

    private boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}