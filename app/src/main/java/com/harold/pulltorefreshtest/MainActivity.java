package com.harold.pulltorefreshtest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


public class MainActivity extends Activity {

    // Set a listener to be invoked when the list should be refreshed.
    private PullToRefreshListView pullToRefreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pullToRefreshView = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_listview);
        pullToRefreshView.setOnPullEventListener(new PullToRefreshBase.OnPullEventListener<ListView>() {
            @Override
            public void onPullEvent(PullToRefreshBase<ListView> refreshView, PullToRefreshBase.State state, PullToRefreshBase.Mode direction) {
                // Do work to refresh the list here.
                Log.d("MainActivity","onRefresh");
                Toast.makeText(getApplicationContext(),"새로고침 시작",Toast.LENGTH_SHORT).show();
                new GetDataTask().execute();
            }
        });
    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {
        @Override
        protected String[] doInBackground(Void... params) {
            Log.d("GetDataTask","doInBackground");
            Toast.makeText(getApplicationContext(),"새로고침 진행중",Toast.LENGTH_SHORT).show();
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] result) {
            // Call onRefreshComplete when the list has been refreshed.
            Log.d("GetDataTask","onPostExecute");
            Toast.makeText(getApplicationContext(),"새로고침 완료",Toast.LENGTH_SHORT).show();
            pullToRefreshView.onRefreshComplete();
            super.onPostExecute(result);
        }
    }
}
