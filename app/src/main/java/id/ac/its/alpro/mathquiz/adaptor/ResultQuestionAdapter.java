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
import id.ac.its.alpro.mathquiz.utility.Category;
import id.ac.its.alpro.mathquiz.utility.Question;

/**
 * Created by ALPRO on 20/04/2016.
 */
public class ResultQuestionAdapter extends RecyclerView.Adapter<ResultQuestionAdapter.ViewHolder> {

    List<Question> questions;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        ImageView status;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView)itemView.findViewById(R.id.name);
            status = (ImageView)itemView.findViewById(R.id.status);
        }
    }

    public ResultQuestionAdapter(List<Question> questions){
        this.questions = questions;
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_result_question, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.cv.setTag(questions.get(i));
        viewHolder.name.setText("Soal " + (i + 1));
        if (questions.get(i).getStatus() != null && questions.get(i).getStatus() == 1)
            viewHolder.status.setImageResource(R.drawable.benar);
        else
            viewHolder.status.setImageResource(R.drawable.salah);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
