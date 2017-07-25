package com.example.kautilya.pollmaker;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://poll.16mb.com/register.php";
    private Map<String, String> params;

    public RegisterRequest(String firstname, String lastname, String email, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        System.out.println(firstname+" "+lastname+" "+email+" "+password);
        params = new HashMap<>();
        params.put("firstname",firstname);
        params.put("last_name",lastname);
        params.put("email",email);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}