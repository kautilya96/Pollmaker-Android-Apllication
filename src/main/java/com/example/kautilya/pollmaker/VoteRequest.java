package com.example.kautilya.pollmaker;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kautilya on 25-3-17.
 */

public class VoteRequest extends StringRequest{
    private static final String LOGIN_REQUEST_URL = "http://poll.16mb.com/update_vote.php";
    private Map<String, String> params;

    public VoteRequest(String user_id, String s_votes,String choice,String poll_id, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("user_id",user_id);
        params.put("s_votes", s_votes);
        params.put("choice",choice);
        params.put("poll_id",poll_id);
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
