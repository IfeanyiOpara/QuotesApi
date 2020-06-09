package Ifeanyi.Opara.quotesapi.model;

import androidx.annotation.NonNull;

public class Post {
    private String quote;
    private String author;

    public Post() {
    }

    public Post(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @NonNull
    @Override
    public String toString() {
        return "Quotes: " + getQuote() + "Author: " + getAuthor();
    }
}
