package com.example.kautilya.pollmaker;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

import static com.example.kautilya.pollmaker.R.styleable.MenuItem;

public class userhome extends ActionBarActivity {



    BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  getSupportActionBar().hide();
        setContentView(R.layout.activity_userhome);

        final SharedPreferences sp1 = getSharedPreferences("login", MODE_PRIVATE);
        final String email2= sp1.getString("email",null);
        final String firstname2=sp1.getString("firstname",null);
        final String lastname2=sp1.getString("lastname",null);
        final int id=sp1.getInt("id",0);
        final Bundle bundle = new Bundle();
        bundle.putString("email", email2);
        bundle.putInt("id",id);
        bundle.putString("firstname",firstname2);

        if (savedInstanceState == null) {

            HomeFragment f = new HomeFragment();
            f.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.Frame, f).commit();
        }

        if (email2==null) {

            startActivity(new Intent(userhome.this,MainActivity.class));
            finish();   //finish current activity
        }
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.menu_userhome, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int i) {

                if (i == R.id.bottombaritemone) {
                    HomeFragment f = new HomeFragment();
                    f.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.Frame, f).commit();
                } else if (i == R.id.bottombaritemtwo) {
                    CreateFragment f = new CreateFragment();
                    f.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.Frame, f).commit();
                } else if (i == R.id.bottombaritemthree) {
                    VoteFragment f = new VoteFragment();
                    f.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.Frame, f).commit();
                } else if (i == R.id.bottombaritemfour) {

                    AlertDialog.Builder adb = new AlertDialog.Builder(userhome.this);


                    //  adb.setView(alertDialogView);


                    adb.setTitle("Do you want to logout ?");


                    adb.setIcon(android.R.drawable.ic_dialog_alert);


                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            final SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
                            SharedPreferences.Editor e = sp.edit();
                            e.clear();
                            e.commit();
                            Intent intent = new Intent(userhome.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    });


                    adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            HomeFragment f = new HomeFragment();
                            f.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.Frame, f).commit();
                        }
                    });
                    adb.show();

                }
            }


            @Override
            public void onMenuTabReSelected(@IdRes int i) {
                if (i == R.id.bottombaritemfour) {

                    AlertDialog.Builder adb = new AlertDialog.Builder(userhome.this);


                    //  adb.setView(alertDialogView);


                    adb.setTitle("Do you want to logout ?");


                    adb.setIcon(android.R.drawable.ic_dialog_alert);


                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            final SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
                            SharedPreferences.Editor e = sp.edit();
                            e.clear();
                            e.commit();
                            Intent intent = new Intent(userhome.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    });


                    adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            HomeFragment f = new HomeFragment();
                            f.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.Frame, f).commit();
                        }
                    });
                    adb.show();

                }
                else if (i == R.id.bottombaritemone) {
                    HomeFragment f = new HomeFragment();
                    f.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.Frame, f).commit();
                } else if (i == R.id.bottombaritemtwo) {
                    CreateFragment f = new CreateFragment();
                    f.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.Frame, f).commit();
                } else if (i == R.id.bottombaritemthree) {
                    VoteFragment f = new VoteFragment();
                    f.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.Frame, f).commit();
                }
            }
        });
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.color));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.color));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.color));
        mBottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.color));


    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int i=item.getItemId();

        final SharedPreferences sp1 = getSharedPreferences("login", MODE_PRIVATE);
        final String email2= sp1.getString("email",null);
        final String firstname2=sp1.getString("firstname",null);
        final String lastname2=sp1.getString("lastname",null);
        final int id=sp1.getInt("id",0);
        final Bundle bundle = new Bundle();
        bundle.putString("email", email2);
        bundle.putInt("id",id);
        bundle.putString("firstname",firstname2);

        if (i == R.id.bottombaritemone) {
            HomeFragment f = new HomeFragment();
            f.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.Frame, f).commit();
        } else if (i == R.id.bottombaritemtwo) {
            CreateFragment f = new CreateFragment();
            f.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.Frame, f).commit();
        } else if (i == R.id.bottombaritemthree) {
            VoteFragment f = new VoteFragment();
            f.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.Frame, f).commit();
        } else if (i == R.id.bottombaritemfour) {

            AlertDialog.Builder adb = new AlertDialog.Builder(userhome.this);


            //  adb.setView(alertDialogView);


            adb.setTitle("Do you want to logout ?");


            adb.setIcon(android.R.drawable.ic_dialog_alert);


            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    final SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor e = sp.edit();
                    e.clear();
                    e.commit();
                    Intent intent = new Intent(userhome.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            });


            adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    HomeFragment f = new HomeFragment();
                    f.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.Frame, f).commit();
                }
            });
            adb.show();

        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_userhome,menu);
        return true;
    }
}

