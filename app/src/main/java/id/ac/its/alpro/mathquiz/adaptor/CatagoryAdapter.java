package id.ac.its.alpro.mathquiz.adaptor;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import id.ac.its.alpro.mathquiz.R;
import id.ac.its.alpro.mathquiz.utility.Category;

/**
 * Created by ALPRO on 20/04/2016.
 */
public class CatagoryAdapter extends RecyclerView.Adapter<CatagoryAdapter.ViewHolder> {

    List<Category> categories;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView categoryName;
        Button playBtn;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            categoryName = (TextView)itemView.findViewById(R.id.category_name);
            playBtn = (Button)itemView.findViewById(R.id.playBtn);
        }
    }

    public CatagoryAdapter(List<Category> categories){
        this.categories = categories;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_catagory, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder personViewHolder, int i) {
        personViewHolder.categoryName.setText(categories.get(i).getCategory_nama());
        personViewHolder.playBtn.setTag(categories.get(i));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
