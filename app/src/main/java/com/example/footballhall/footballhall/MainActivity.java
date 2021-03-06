package com.example.footballhall.footballhall;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.footballhall.footballhall.objetos.Agenda;
import com.example.footballhall.footballhall.objetos.Cliente;
import com.example.footballhall.footballhall.objetos.ClienteDbHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(getBaseContext(), AgendamentoActivity.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(getBaseContext(), MapsActivity.class));
        //} else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(getBaseContext(), CadCliente_Activity.class));
        } else if (id == R.id.nav_share) {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

            share.putExtra(Intent.EXTRA_SUBJECT, "Aqui vai sua mensagem");
            share.putExtra(Intent.EXTRA_TEXT, "Aqui vai mensagem ou link que deseja compartilhar");
            startActivity(Intent.createChooser(share, "Compartilhar"));

        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:Footballhall@gmail.com"));
            intent.putExtra(Intent.EXTRA_EMAIL, "Footballhall@gmail.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
            if (intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
        } else if (id == R.id.telefone){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel: (91) 3222-5555"));
            if (intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        
    }

    public void QuadraVindi(View view) {
        startActivity(new Intent(getBaseContext(),QuadraVindi_Activity.class));
    }

    public void QuadraSabino(View view) {
        startActivity(new Intent(getBaseContext(),QuadraSabino_Activity.class));
    }

    public void QuadraSenac(View view) {
        startActivity(new Intent(getBaseContext(),QuadraSenac_Activity.class));
    }


}
