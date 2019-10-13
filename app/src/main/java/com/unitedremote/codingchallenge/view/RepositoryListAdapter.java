package com.unitedremote.codingchallenge.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.unitedremote.codingchallenge.R;
import com.unitedremote.codingchallenge.model.Repository;

import java.text.DecimalFormat;
import java.util.List;

public class RepositoryListAdapter extends RecyclerView.Adapter<RepositoryListAdapter.RepositoryViewHolder> {

    private List<Repository> repositories;
    private Context context;

    public RepositoryListAdapter(List<Repository> repositories, Context cnx) {
        this.repositories = repositories;
        this.context = cnx;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_repository, parent, false);

        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {

        // display data
        holder.setName(repositories.get(position).getmName());
        holder.setDesc(repositories.get(position).getmDescription());

        float nbsStars = repositories.get(position).getmStarsNumber();
        if (nbsStars > 1000) nbsStars /= 1000;
        repositories.get(position).setmStarsNumber(nbsStars);
        // get only the first digit afrer comma and then append the value with 'k'
        holder.setNbsStars(new DecimalFormat("##.#").format(nbsStars) + "k");
        holder.setUserName(repositories.get(position).getOwner().getLogin());
        holder.setuserAvatar(repositories.get(position).getOwner().getAvatar_url(), context);

    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public static class RepositoryViewHolder extends  RecyclerView.ViewHolder {
        public TextView mName, mDesc, mUserName, mNbrStars;
        public ImageView mUserAvatar;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.single_rep_title);
            mDesc = itemView.findViewById(R.id.single_rep_desc);
            mUserName = itemView.findViewById(R.id.single_rep_user_name);
            mNbrStars = itemView.findViewById(R.id.single_rep_stars_nbrs);

            mUserAvatar = itemView.findViewById(R.id.single_rep_user_avatar);

        }


        public void setName(String name){
            mName.setText(name);
        }
        public void setDesc(String desc){
            mDesc.setText(desc);
        }
        public void setUserName(String username){
            mUserName.setText(username);
        }
        public void setNbsStars(String nbsStars){
            mNbrStars.setText(nbsStars);
        }
        public void setuserAvatar(String avatar, Context cnx){
            Glide.with(cnx).load(avatar).into(mUserAvatar);
        }
    }


}

