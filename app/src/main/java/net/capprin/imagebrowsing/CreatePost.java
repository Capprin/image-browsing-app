package net.capprin.imagebrowsing;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class CreatePost extends Fragment {

    //NOTE: Callback setup not really necessary anymore; was in place when the button "post" function was present in this class. Will remain for style and learning's sake

    //Points to parent activity
    private OnCreatePostListener mCallback;

    //Creates layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_post, container, false);
    }

    //When created, grabs the activity to use in callback
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            mCallback = (OnCreatePostListener) activity;
        }
        catch(ClassCastException e){
            throw new ClassCastException(activity + " must implement OnCreatePostListener");
        }
    }

    //Used to communicate with parent activity
    protected interface OnCreatePostListener{
        void onCreatePost(String title, String text);
    }

}
