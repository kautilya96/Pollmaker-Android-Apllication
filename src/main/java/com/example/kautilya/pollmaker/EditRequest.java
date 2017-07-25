package com.example.kautilya.pollmaker;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EditRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://poll.16mb.com/edit_poll.php";
    private Map<String, String> params;

    public EditRequest(String question, String choice1, String choice2, String choice3, String choice4, int id, String date, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        System.out.println(question+" "+choice1+" "+choice2+" "+choice3+" "+choice4);
        String id1=String.valueOf(id);
        params = new HashMap<>();
        params.put("question",question);
        params.put("choice1",choice1);
        params.put("choice2",choice2);
        params.put("choice3",choice3);
        params.put("choice4",choice4);
        params.put("poll_id",id1);
        params.put("creation_date",date);
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}