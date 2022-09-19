package com.example.mynews;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<MyData> data = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        layoutManager= new LinearLayoutManager(MainActivity.this);
        RequestQueue req = Volley.newRequestQueue(this);
        String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=98ff46b12d584a0abf812d41fdf42d50";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    if (response.getString("status").equals("ok")) {

                        JSONArray jsonArray = response.getJSONArray("articles");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject item = jsonArray.getJSONObject(i);
                            MyData md = new MyData(item.getString("urlToImage"), item.getString("title"), item.getString("publishedAt"));

                            data.add(md);
                        }

                       adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Error in " + e, Toast.LENGTH_SHORT).show();
                    Log.d("eroorr1", e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error in" + error, Toast.LENGTH_SHORT).show();
                Log.d("errrr11", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };
        req.add(jsonObjectRequest);
        adapter = new RecyclerAdapter(MainActivity.this, data);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}