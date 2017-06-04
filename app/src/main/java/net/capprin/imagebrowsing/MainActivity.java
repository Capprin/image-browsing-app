package net.capprin.imagebrowsing;


import android.app.Fragment;
import android.graphics.Point;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements CreatePost.OnCreatePostListener{

    private Point size;
    private float startX;
    private ViewPosts viewPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Screen Size
        size = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);

        //Make sure you can actually use the fragment_container
        if (findViewById(R.id.fragment_container) != null){

            //Something about not needing to do anything if we're restored from a previous state
            if (savedInstanceState != null) return;

            //Make new fragment to put in layout
            viewPosts = new ViewPosts();

            //Pass instructions from an intent
            viewPosts.setArguments(getIntent().getExtras());

            //Add teh fragment to the container
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,viewPosts,"viewposts").commit();
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
                    if (event.getRawX() > startX) onSwipeRight();
                    else onSwipeLeft();
                }
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    private void onSwipeLeft(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,new CreatePost(),"createpost");
        transaction.commit();
    }

    private void onSwipeRight(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,viewPosts,"viewposts");
        transaction.commit();
    }

    public void post(View view){
        EditText title = (EditText)findViewById(R.id.title);
        EditText content = (EditText)findViewById(R.id.content);

        onCreatePost(title.getText().toString(),content.getText().toString());
    }

    @Override
    public void onCreatePost(String title, String text) {
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putString("content",text);
        viewPosts.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, viewPosts, "viewposts");
        transaction.commit();
    }
}
