package com.dsman.uaapp.General_Course;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsman.uaapp.Courses.Classes.ClassFragment;
import com.dsman.uaapp.Comunities.FragmentComunity.ComunityFragment;
import com.dsman.uaapp.FormsActivity;
import com.dsman.uaapp.MainActivity;
import com.dsman.uaapp.Notifications.NotificationFragment;
import com.dsman.uaapp.Professor.Professor.ProfessorFragment;
import com.dsman.uaapp.Qualifications.QualificationsFragment;
import com.dsman.uaapp.R;
import com.dsman.uaapp.User;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.OnClick;

public class General_Course extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private  TextView mName;
    private TextView mSurname;
    private ImageView mImageView;
    public static final String USER = "USER";
    private static User user;
    private String mUri, sUserName, sSurname, sEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_course);
        mName = findViewById(R.id.textView1);
        mSurname = findViewById(R.id.textView2);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        LayoutInflater.from(getBaseContext()).inflate(R.layout.activity_general_course_navheader, navigationView);

        navigationView.setNavigationItemSelectedListener(this);
        mImageView = navigationView.findViewById(R.id.imageViewU);
        mName = navigationView.findViewById(R.id.textView1);
        mSurname = navigationView.findViewById(R.id.textView2);
        /*mRecyclerView = (RecyclerView) findViewById(R.id.class_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerActivity
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);*/

        //TODO Arreglar esto XD;
        user = new User();
       Intent intent_receive = this.getIntent();
        if(intent_receive != null){
            user = (User) intent_receive.getParcelableExtra(MainActivity.USER);
             mUri = user.getUrl();
             sEmail = user.getEmail();
             sUserName = user.getName();
             sSurname = user.getSurname();

        }

            mName.setText(sUserName);
            mSurname.setText(sSurname);

        try {
            Context mContext = FormsActivity.getContextOfApplication();
            if (mUri != null) {

                Uri mUserImage = Uri.parse(mUri);
                final InputStream imageStream = mContext.getContentResolver().openInputStream(mUserImage);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                mImageView.setImageBitmap(selectedImage);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        changeFragment(item.getItemId());
        return true;
    }

    public void changeFragment(int id){
        Fragment fragment = null;

        //iniciamos los fragments dependiendo del item selecionado
        switch (id) {
            case R.id.nav_clases:
                setTitle("Clases");
                fragment = new ClassFragment();
                break;
            case R.id.nav_notificaciones:
                setTitle("Notificationes");
                fragment = new NotificationFragment();
                break;
            case R.id.nav_notas:
                setTitle("Notas");
                fragment = new QualificationsFragment();
                break;
            case R.id.nav_profesores:
                setTitle("Profesores");
                fragment = new ProfessorFragment();
                break;
            case R.id.nav_comunidades:
                setTitle("Comunidades");
                fragment = new ComunityFragment();
                break;
        }

        //Remplazamos el fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_generalcourse, fragment);
            ft.commit();
        }
    }

    @OnClick()
    public void onclicklogout(MenuItem item) {
        //TODO Intentar arreglar el Warning
        Intent navigate = new Intent(General_Course.this, MainActivity.class);
        navigate.setFlags(navigate.FLAG_ACTIVITY_NEW_TASK | navigate.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(navigate);
        finish();
    }
}