package com.dsc.android.bootcamp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<RecyclerViewData> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.recycler_view);
        // createMockList();
        setUpRecyclerView();
        apicall();
    }

    private void apicall(){

        ApiServices apiServices = AppClient.getInstance().createService(ApiServices.class);
        Call<UserWrapper> call = apiServices.getUserList();
        call.enqueue(new Callback<UserWrapper>() {
            @Override
            public void onResponse(Call<UserWrapper> call, Response<UserWrapper> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        userList.addAll(response.body().getRecyclerViewData());
                        Toast.makeText(RecyclerViewActivity.this, "Successful response", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserWrapper> call, Throwable t) {

            }
        });
    }

    /** private void createMockList(){
        RecyclerViewData data;
        data = new RecyclerViewData("https://bit.ly/2NT7svr", "Vishal Chandrakar", "6874321878");
        mockDataList.add(data);
        data = new RecyclerViewData("https://bit.ly/2NT7svr", "Shreyas Shreya", "6543168434");
        mockDataList.add(data);
        data = new RecyclerViewData("https://bit.ly/2NT7svr", "Vikas", "751687653");
        mockDataList.add(data);
        data = new RecyclerViewData("https://bit.ly/2NT7svr", "Tuhin - Mammoth", "7813594351");
        mockDataList.add(data);
        data = new RecyclerViewData("https://bit.ly/2NT7svr", "Shreyas Shreya", "6543168434");
        mockDataList.add(data);
        data = new RecyclerViewData("https://bit.ly/2NT7svr", "Vikas", "751687653");
        mockDataList.add(data);
        data = new RecyclerViewData("https://bit.ly/2NT7svr", "Vishal Chandrakar", "6874321878");
        mockDataList.add(data);
        data = new RecyclerViewData("https://bit.ly/2NT7svr", "Shreyas Shreya", "6543168434");
        mockDataList.add(data);
        data = new RecyclerViewData("https://bit.ly/2NT7svr", "Vikas", "751687653");
        mockDataList.add(data);
    } */

    private void setUpRecyclerView(){
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setRecyclerViewDataList(userList);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
