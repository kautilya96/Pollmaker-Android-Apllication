package com.example.kautilya.pollmaker;

/*import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;



public class HomeFragment extends Fragment  implements View.OnClickListener {

   /* private static final int RESULT_LOAD_IMAGE=1;
    private static final String SER_ADD="http://poll.16mb.com/";

   // final int id1=getArguments().getInt("id",0);
   int id1=1,flag=0;
    String id=String.valueOf(id1);
    private ImageView addimage;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.home,container,false);

    /*    button=(Button) v.findViewById(R.id.addimge);
        addimage=(ImageView) v.findViewById(R.id.image);
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryintent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryintent,RESULT_LOAD_IMAGE);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap image=((BitmapDrawable) addimage.getDrawable()).getBitmap();
                System.out.println("1");
                new UploadImage(image,id).execute();

            }
        });


        return v;
    }


@Override
    public void onClick(View v) {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data!=null ){
            Uri selectedimage=data.getData();
            addimage.setImageURI(selectedimage);

        }
    }


    private class UploadImage extends AsyncTask<Void,Void,Void>{

        String id,name;
        Bitmap image;
        public UploadImage(Bitmap image,String id)
        {
            this.image=image;
            this.id=id;
            this.name="cool";
        }
        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream bs=new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG,100,bs);
            String encodedImage= Base64.encodeToString(bs.toByteArray(),Base64.DEFAULT);

            //send.add(new BasicNameValuePair("image",encodedImage));
            //send.add(new BasicNameValuePair("name",name));
            //send.add(new BasicNameValuePair("id",id));
            Response.Listener<String> listener=new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                }
            };
            InsertimRequest ll=new InsertimRequest(encodedImage,id,name,listener);
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.add(ll);
             return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
*/
import android.Manifest;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment implements View.OnClickListener  {

    private Context cont;

    private FloatingActionButton buttonUpload;

    private ImageView imageView;


    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private int PERMISSION_CODE=1;

    private String UPLOAD_URL ="http://poll.16mb.com/addimage.php";

    private String KEY_IMAGE = "image";

     TextView tt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v=inflater.inflate(R.layout.home,container,false);
        super.onCreate(savedInstanceState);
        final SharedPreferences sp1 = getActivity().getSharedPreferences("login", MODE_PRIVATE);
        final String email2= sp1.getString("email",null);
        final String firstname2=sp1.getString("firstname",null);
        final String lastname2=sp1.getString("lastname",null);
        final int id1=sp1.getInt("id",0);
        final String id=String.valueOf(id1);
        imageView  = (ImageView) v.findViewById(R.id.imageView);
      //  final Button button=(Button) v.findViewById(R.id.button);
          final TextView button=(TextView) v.findViewById(R.id.button);
        tt=(TextView) v.findViewById(R.id.user);
        tt.setText(firstname2+" "+lastname2);
        requestStoragePermission();

        TextView mpolls=(TextView) v.findViewById(R.id.mpolls);
        mpolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),ManageActivity.class);
                getActivity().startActivity(i);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),GraphActivity.class);
                getActivity().startActivity(i);
            }
        });
          Response.Listener<String> listener=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);

                    if (array.length()!=0) {
                        JSONObject o=array.getJSONObject(0);
                       String image_path = o.getString("image_path");
                     //   Bitmap bs = getImage(image_path);
                     //      imageView.setImageBitmap(bs);
                     //  Picasso.with(cont).load(image_path).into(imageView);

                        new DownloadImageTask((ImageView) v.findViewById(R.id.imageView))
                                .execute(image_path);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
         getimRequest ll =new getimRequest(id,listener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(ll);

        //   buttonChoose = (Button) v.findViewById(R.id.buttonChoose);
        buttonUpload = (FloatingActionButton) v.findViewById(R.id.buttonUpload);



      //  buttonChoose.setOnClickListener(this);
        imageView.setOnClickListener(this);

        buttonUpload.setOnClickListener(this);
        return v;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 30, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(getActivity(), "Uploaded Image" , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
//                        Toast.makeText(getActivity(), volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);
                final SharedPreferences sp1 = getActivity().getSharedPreferences("login", MODE_PRIVATE);
                final String email2= sp1.getString("email",null);
                final String firstname2=sp1.getString("firstname",null);
                final String lastname2=sp1.getString("lastname",null);
                final int id1=sp1.getInt("id",0);
                final String id=String.valueOf(id1);

                //Getting Image Name

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put("id",id);
                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    private void requestStoragePermission(){
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==PERMISSION_CODE){
            if(grantResults.length>0 && grantResults.length==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getActivity(),"Permission Granted",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getActivity(),"Permission Not Granted",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {

        if(v == imageView){
            showFileChooser();
        }

        if(v == buttonUpload){
            uploadImage();
        }
    }
}
