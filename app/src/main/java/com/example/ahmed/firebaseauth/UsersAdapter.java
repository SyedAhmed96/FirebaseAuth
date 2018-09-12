package com.example.ahmed.firebaseauth;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//import com.bumptech.glide.Glide;

import java.util.List;

public class UsersAdapter extends ArrayAdapter<Users> {
    public UsersAdapter(Context context, int resource, List<Users> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_contacts, parent, false);
        }
     //ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView emailTextView = (TextView) convertView.findViewById(R.id.emailTextView);

        Users user = getItem(position);

        boolean isPhoto = user.getPhotoUrl() != null;
        if (isPhoto) {
       //      messageTextView.setVisibility(View.GONE);
            //      photoImageView.setVisibility(View.VISIBLE);
      //      Glide.with(photoImageView.getContext()).load(message.getPhotoUrl()).into(photoImageView);
        } else {
        //    messageTextView.setVisibility(View.VISIBLE);
       //     photoImageView.setVisibility(View.GONE);
        }

        nameTextView.setText(user.getName());
        emailTextView.setText(user.getUid());

        return convertView;
    }
}
