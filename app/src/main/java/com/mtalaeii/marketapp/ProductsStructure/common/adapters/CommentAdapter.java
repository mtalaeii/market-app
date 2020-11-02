package com.mtalaeii.marketapp.ProductsStructure.common.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtalaeii.marketapp.ProductsStructure.common.model.Comment;
import com.mtalaeii.marketapp.R;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<Comment> comments = new ArrayList<>();
    public CommentAdapter(List<Comment> comments){
        this.comments=comments;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_comment,parent,false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.bindComment(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        private TextView commentTitleTextView;
        private TextView commentContentTextView;
        private TextView commentDateTextView;
        private TextView commentFullNameTextView;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            commentContentTextView = itemView.findViewById(R.id.comment_contentTV);
            commentDateTextView = itemView.findViewById(R.id.comment_dateTV);
            commentFullNameTextView = itemView.findViewById(R.id.comment_fullNameTV);
            commentTitleTextView = itemView.findViewById(R.id.comment_titleTV);
        }
        public void  bindComment(Comment comment){
            commentTitleTextView.setText(comment.getTitle());
            commentFullNameTextView.setText(comment.getFullName());
            commentDateTextView.setText(comment.getDate());
            commentContentTextView.setText(comment.getContent());
        }
    }
}
