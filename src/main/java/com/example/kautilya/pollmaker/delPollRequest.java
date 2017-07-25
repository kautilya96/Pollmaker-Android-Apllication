package com.example.kautilya.pollmaker;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class delPollRequest extends StringRequest{
    private static final String LOGIN_REQUEST_URL = "http://poll.16mb.com/delete_poll.php";
    private Map<String, String> params;

    public delPollRequest(String id, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("poll_id",id);
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
