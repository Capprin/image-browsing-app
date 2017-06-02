package net.capprin.imagebrowsing;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_post);

        //Make sure you can actually use the fragment_container
        if (findViewById(R.id.fragment_container) != null){

            //Something about not needing to do anything if we're restored from a previous state
            if (savedInstanceState != null) return;

            //Make new fragment to put in layout
            ViewPosts viewPosts = new ViewPosts();

            //Pass instructions from an intent
            viewPosts.setArguments(getIntent().getExtras());

            //Add teh fragment to the container
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,viewPosts).commit();
        }

    }
}
