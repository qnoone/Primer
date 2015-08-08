package ru.prospectgp.test8;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.prospectgp.test8.rss.model.Item;

public class FeedDetailActivity extends Activity {
    public static final String ARG_ITEM = "ARG_ITEM";
    public static final String NEWLINE = "\\n";
    public static final String BR = "<br />";
    public static final String HTML_MIME_TYPE = "text/html";

    private DateFormat dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_detail);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Item item = (Item) getIntent().getSerializableExtra(ARG_ITEM);

        TextView title = (TextView) findViewById(R.id.feed_detail_title);
        TextView date = (TextView) findViewById(R.id.feed_detail_date);
        WebView webView = (WebView) findViewById(R.id.feed_detail_body);

        title.setText(item.getTitle());
        date.setText(dateFormat.format(new Date(item.getPubDate())));
        String html = item.getContent();

        html = html.replaceAll(NEWLINE, BR);
        html = html.replaceAll("<link></link>", "");

        webView.loadData(html, HTML_MIME_TYPE, null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
