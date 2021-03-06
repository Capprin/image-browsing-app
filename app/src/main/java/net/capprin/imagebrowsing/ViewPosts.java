package net.capprin.imagebrowsing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ViewPosts extends Fragment {
    private String title;
    private String content;

    //When view is created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_view_posts, container, false);

        //Get title and content textViews
        TextView titleView = (TextView)myView.findViewById(R.id.output_title);
        TextView contentView = (TextView)myView.findViewById(R.id.output_content);

        //Set text
        titleView.setText(title);
        contentView.setText(content);

        // Inflate the layout for this fragment
        return myView;
    }

    //Sets arguments
    @Override
    public void setArguments(Bundle args){
        try{
            title = args.getString("title");
            content = args.getString("content");
        }
        catch(Exception e){
            title = "";
            content = "";
        }
    }

}
