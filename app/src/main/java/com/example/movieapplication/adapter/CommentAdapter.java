package com.example.movieapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.movieapplication.R;
import com.example.movieapplication.activity.MainActivity;
import com.example.movieapplication.model.comment.CommentData;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    Context context;
    ArrayList<CommentData> data;

    public CommentAdapter(Context context, ArrayList<CommentData> listComment) {
        this.context = context;
        this.data = listComment;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_listitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        CommentData model = data.get(position);
        if (model.getImgProfile()!=null){
            String img = model.getImgProfile().toString();
            if (img.equals("ImageProfile/PenelopeAvatar.jpg")){
                circleProfile(R.drawable.penelopeavatar,holder.commentProfile);
            } else if (img.equals("ImageProfile/LelouchAvatar.jpg")){
                circleProfile(R.drawable.lelouchavatar,holder.commentProfile);
            } else if (img.equals("ImageProfile/SaberAvatar.jpg")){
                circleProfile(R.drawable.saberavatar,holder.commentProfile);
            } else if (img.equals("ImageProfile/SaitamaAvatar.jpg")){
                circleProfile(R.drawable.saitamaavatar,holder.commentProfile);
            } else if (img.equals("ImageProfile/MikasaAvatar.jpg")){
                circleProfile(R.drawable.mikasaavatar,holder.commentProfile);
            } else if (img.equals("ImageProfile/YagamiAvatar.jpg")){
                circleProfile(R.drawable.yagamiavatar,holder.commentProfile);
            }
        }

        holder.NameUser.setText(model.getUserName());
        holder.Comment.setText(model.getComment());
        holder.like.setText(model.getLike());
        holder.dislike.setText(model.getDislike());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView commentProfile;
        TextView NameUser,Comment,like,dislike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            commentProfile = itemView.findViewById(R.id.ivUser);
            NameUser = itemView.findViewById(R.id.tvUserComment);
            Comment = itemView.findViewById(R.id.tvComment);
            like = itemView.findViewById(R.id.tvLikeComment);
            dislike = itemView.findViewById(R.id.tvDislikeComment);
        }
    }
    void circleProfile(int drawable,ImageView img){
        Glide.with(context)
                .load(drawable)
                .apply(RequestOptions.circleCropTransform())
                .into(img);
    }
}
