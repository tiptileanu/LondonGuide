package mic.alliwanna.be.londonguide;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PoiAdapter extends RecyclerView.Adapter<PoiAdapter.Holder> {

    ArrayList<POI> list;
    Holder.PoiInterface listener;

    public PoiAdapter(ArrayList<POI> list, Holder.PoiInterface _listener) {

        this.list = list;
        listener = _listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.poi_item, viewGroup, false);
        Holder holder = new Holder(v, listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

        holder.tv.setText(list.get(i).getPoiName());
        Picasso.get().load(list.get(i).getPoiPhotoUrl()).fit().into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iv;
        TextView tv;
        PoiInterface listener;

        public Holder(@NonNull View itemView, PoiInterface _listener) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_image);
            tv = itemView.findViewById(R.id.tv_name);
            listener = _listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onPoiClick(getAdapterPosition());

        }

        public interface PoiInterface {
            public void onPoiClick(int i);
        }
    }

}

