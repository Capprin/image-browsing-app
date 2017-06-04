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

    private GestureDetectorCompat gestureDetector;
    private Point size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_post);

        //Set up GestureListener with this context and an instance of OnGestureListener
        gestureDetector = new GestureDetectorCompat(this,new MyGestureListener());

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
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event){
            Log.d(DEBUG_TAG,"onDown: " + event);
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent startEvent, MotionEvent currentEvent, float dx, float dy){
            Log.d(DEBUG_TAG,"Scrolled!");
            return true;
        }


    }



}
