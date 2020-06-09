package Ifeanyi.Opara.quotesapi;

import Ifeanyi.Opara.quotesapi.adapters.postAdapter;
import Ifeanyi.Opara.quotesapi.model.Post;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private ApiService apiService;

    private RecyclerView recyclerView;
    private postAdapter postAdapter;
    private List<Post> posts;

    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;

    private TextView author;
    private EditText quotesNumber;
    private Button get_quotes_button,im_feeling_lucky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://breaking-bad-quotes.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        quotesNumber = findViewById(R.id.quote_number);
        get_quotes_button = findViewById(R.id.get_quote_btn);
        im_feeling_lucky = findViewById(R.id.im_feeling_lucky_btn);

//        author = findViewById(R.id.quotes_author);

        get_quotes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(quotesNumber.getText().toString());
                getQuotes(num);
            }
        });

        im_feeling_lucky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Random rand = new Random();
//                int a = rand.nextInt(111);
//                quotes.setText(String.valueOf(a));

                getRandomQuote();
            }
        });

    }

    private void getQuotes(final int quotesNumber){
        apiService.getQuotes(quotesNumber).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                int StatusCode = response.code();
                posts = (List<Post>) response.body();

//                author.setText("Status Code: " + StatusCode + "\n");
                for (Post post: posts){
//                    author.append("Author" + "\n" + post.getAuthor());
//                    author.append("Quotes" + "\n" + post.getQuote() + "\n");

                    postAdapter = new postAdapter(getApplicationContext(), posts);
                    recyclerView.setAdapter(postAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });


    }

    private void getRandomQuote(){
        apiService.getRandomQuote().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                List<Post> post = (List<Post>) response.body();

                for (Post posts : post){
                    String title = "Random Quote";
                    alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setTitle(title)
                            .setMessage(posts.getQuote())
                            .setNeutralButton("Thanks", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    alertDialogBuilder.show();

//                    quotes.append("Author" + "\n" + posts.getAuthor());
//                    quotes.append("Quotes" + "\n" + posts.getQuote() + "\n");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}
