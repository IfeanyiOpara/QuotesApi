package Ifeanyi.Opara.quotesapi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import Ifeanyi.Opara.quotesapi.R;
import Ifeanyi.Opara.quotesapi.model.Post;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class postAdapter extends RecyclerView.Adapter<postAdapter.ViewHolder> {

    private Context mContext;
    private List<Post> listPost;

    public postAdapter(Context mContext, List<Post> listPost) {
        this.mContext = mContext;
        this.listPost = listPost;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.quotes_row,parent,false);
        return new postAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = listPost.get(position);
        holder.quotes_author.append( post.getAuthor());
        holder.quotes_body.setText(post.getQuote());
    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView quotes_body,quotes_author;

        public ViewHolder(View itemView) {
            super(itemView);

            quotes_body = itemView.findViewById(R.id.quotes_body);
            quotes_author = itemView.findViewById(R.id.quotes_author);

        }
    }
}
