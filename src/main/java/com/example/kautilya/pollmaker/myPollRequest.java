package com.example.kautilya.pollmaker;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;


import java.util.HashMap;
import java.util.Map;

public class myPollRequest extends StringRequest{
    private static final String LOGIN_REQUEST_URL = "http://poll.16mb.com/getmypoll.php";
    private Map<String, String> params;

    public myPollRequest(String id, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id",id);
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
