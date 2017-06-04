package net.capprin.imagebrowsing;


import android.graphics.Point;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity{

    private Point size;
    private float startX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_post);

        //Get Screen Size
        size = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);

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

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = event.getAction();

        switch(action){
            case MotionEvent.ACTION_DOWN:
                startX = event.getRawX();
                return true;
            case MotionEvent.ACTION_UP:
                if (Math.abs(event.getRawX()-startX) > size.x/2){
                    Log.d("Gestures","Swiped!");
                    onSwipe();
                }
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    private void onSwipe(){

    }

}
