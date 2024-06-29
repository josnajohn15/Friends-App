package com.example.friendsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    AppCompatButton b1;
    String apiUrl="https://friendsapi-re5a.onrender.com/adddata";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ed1=(EditText) findViewById(R.id.myname);
        ed2=(EditText) findViewById(R.id.fname);
        ed3=(EditText) findViewById(R.id.nick);
        ed4=(EditText) findViewById(R.id.des);
        b1=(AppCompatButton) findViewById(R.id.reg);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getname=ed1.getText().toString();
                String getfriend=ed2.getText().toString();
                String getnick=ed2.getText().toString();
                String getdes=ed2.getText().toString();
                 //JSON Object creation
                JSONObject fri=new JSONObject();
                try {
                    fri.put("name",getname);
                    fri.put("friendName",getfriend);
                    fri.put("friendNickName",getnick);
                    fri.put("DescribeYourFriend",getdes);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                //Json object request creation
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST,
                        apiUrl,
                        fri,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "added successfully", Toast.LENGTH_SHORT).show();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }

                );
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });


    }
}
