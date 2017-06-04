package net.capprin.imagebrowsing;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class CreatePost extends Fragment {

    private OnCreatePostListener mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_post, container, false);
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            mCallback = (OnCreatePostListener) activity;
        }
        catch(ClassCastException e){
            throw new ClassCastException(activity + " must implement OnCreatePostListener");
        }
    }



    public interface OnCreatePostListener{
        public void onCreatePost(String title, String text);
    }

}
