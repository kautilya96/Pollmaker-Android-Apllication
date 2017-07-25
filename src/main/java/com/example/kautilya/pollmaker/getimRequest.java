package com.example.kautilya.pollmaker;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class getimRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://poll.16mb.com/getimage.php";
    private Map<String, String> params;

    public getimRequest(String id,Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
     //   System.out.print(image+" "+id+"  "+name);
        params = new HashMap<>();
      //  params.put("image",image);
        params.put("id",id);
      //  params.put("name",name);
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}