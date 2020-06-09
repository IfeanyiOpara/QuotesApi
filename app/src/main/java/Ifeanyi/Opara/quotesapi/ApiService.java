package Ifeanyi.Opara.quotesapi;

import java.util.List;

import Ifeanyi.Opara.quotesapi.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/v1/quotes/{number}")
    Call<List<Post>> getQuotes(
        @Path("number") int quotesNumber
    );

    @GET("/v1/quotes/1")
    Call<List<Post>> getRandomQuote();

}
