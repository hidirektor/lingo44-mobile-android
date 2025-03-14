package com.hidirektor.lingify.Utility.Models.Quote;

public class QuoteModel {
    private final String quote;
    private final String owner;

    public QuoteModel(String quote, String owner) {
        this.quote = quote;
        this.owner = owner;
    }

    public String getQuote() {
        return quote;
    }

    public String getOwner() {
        return owner;
    }
}
