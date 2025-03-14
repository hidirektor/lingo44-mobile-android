package com.hidirektor.lingify.Utility;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.widget.TextView;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Models.Quote.QuoteModel;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneralUtil {

    public static void getRandomQuote(Context context, TextView quoteText, TextView quoteOwnerText) {
        // Get the language of the phone (e.g., "en" or "tr")
        String language = context.getResources().getConfiguration().locale.getLanguage();

        // Load quotes from the XML file and filter based on the language
        List<QuoteModel> quotes = loadQuotes(context, language);

        if (quotes.size() > 0) {
            QuoteModel randomQuote = quotes.get(new Random().nextInt(quotes.size()));

            quoteText.setText("\"" + randomQuote.getQuote() + "\"");
            quoteOwnerText.setText("– " + randomQuote.getOwner().replace("--", "–"));
        }
    }

    private static List<QuoteModel> loadQuotes(Context context, String language) {
        List<QuoteModel> quotes = new ArrayList<>();
        try {
            // Load the quotes XML file
            XmlResourceParser parser = context.getResources().getXml(R.xml.quotes);
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && "quote".equals(parser.getName())) {
                    // Get language attribute and text
                    String quoteLang = parser.getAttributeValue(null, "lang");
                    if (quoteLang.equals(language)) {
                        String quote = parser.nextText(); // The actual quote
                        String owner = quote.substring(quote.lastIndexOf("–") + 1).trim(); // Extract the owner name
                        quote = quote.substring(0, quote.lastIndexOf("–")).trim(); // Extract the quote text
                        quotes.add(new QuoteModel(quote, owner));
                    }
                }
                eventType = parser.next();
            }
            parser.close();
        } catch (Exception e) {
            Log.e("GeneralUtil", "Error loading quotes XML", e);
        }
        return quotes;
    }
}
