package id.ac.its.alpro.mathquiz.adaptor;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.ac.its.alpro.mathquiz.R;
import id.ac.its.alpro.mathquiz.utility.Question;
import id.ac.its.alpro.mathquiz.utility.Score;

/**
 * Created by ALPRO on 20/04/2016.
 */
public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    List<Score> scores;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        TextView score;
        ImageView status;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView)itemView.findViewById(R.id.name);
            score = (TextView)itemView.findViewById(R.id.score);
            status = (ImageView)itemView.findViewById(R.id.status);
        }
    }

    public ScoreAdapter(List<Score> scores){
        this.scores = scores;
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_score, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.name.setText(scores.get(i).getName());
        viewHolder.score.setText(scores.get(i).getPoint()+"");

        if (i==0)
            viewHolder.status.setImageResource(R.drawable.gold);
        else if (i==1)
            viewHolder.status.setImageResource(R.drawable.silver);
        else if (i==2)
            viewHolder.status.setImageResource(R.drawable.bronze);
        else
            viewHolder.status.setVisibility(View.GONE);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
