package mic.alliwanna.be.londonguide;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.Holder> {

    ArrayList<Reviews> reviewsList;

    public ReviewsAdapter(ArrayList<Reviews> reviewsList) {
        this.reviewsList = reviewsList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_item, viewGroup, false);
        Holder holder = new Holder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.userName.setText(reviewsList.get(i).getUserId());
        holder.userReview.setText(reviewsList.get(i).getReviewOfPoi());
        holder.userRating.setRating(reviewsList.get(i).getRatingOfPoi());

    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        TextView nameOfPoi, userName, userReview;
        RatingBar userRating;

        public Holder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.tv_user_id);
            userReview = itemView.findViewById(R.id.tv_user_review);
            userRating = itemView.findViewById(R.id.rb_user_review);
        }
    }

}
