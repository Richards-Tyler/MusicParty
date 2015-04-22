package com.example.tr.musicparty;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity implements MainMenu.OnFragmentInteractionListener,
    JoinView.OnFragmentInteractionListener{

    protected FragmentManager fragmentManager;
    protected FragmentTransaction fragmentTransaction;
    private static final int REQUEST_ENABLE_BT = 1;
    boolean temp=false;

    /***************************************************************************************************
    *
    * `                                         On Create
    *
    **************************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        MainMenu mainMenu = new MainMenu();
        fragmentTransaction.add(R.id.FragmentHolder, mainMenu);
        fragmentTransaction.commit();

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();//Declare and get Adapter
        if(mBluetoothAdapter == null){//checks if bluetooth adapter is supported
             temp =true;
        }

       else if (!mBluetoothAdapter.isEnabled()||temp){//checks if bluetooth is off
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }
    /***************************************************************************************************
     *
     * `                                         join server
     *
     **************************************************************************************************/
    protected void joinServer(View view){

        JoinView joinView = new JoinView();
        fragmentTransaction.add(R.id.FragmentHolder, joinView);
        fragmentTransaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
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
    }

    //@Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(String id) {

    }
}
