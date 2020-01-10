package com.example.docbaorss.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.docbaorss.R;
import com.example.docbaorss.XMLDOMParser;
import com.example.docbaorss.adapter.ReadPaperAdapter;
import com.example.docbaorss.model.ReadPaper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ReadPaperAdapter readPaperAdapter;
    ArrayList<ReadPaper> papers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvTitle);
        papers = new ArrayList<>();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new  ReadRss().execute("https://vnexpress.net/rss/the-thao.rss");
            }
        });


        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("link", papers.get(position).link);
            startActivity(intent);
        });

    }

    private class ReadRss extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line= "";

                while ((line = bufferedReader.readLine())!= null){
                    content.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {

            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListDescription = document.getElementsByTagName("description");

            String title = "";
            String link = "";
            String image = "";

            for(int i = 0; i< nodeList.getLength(); i++){
                String cdata = nodeListDescription.item(i+1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                if(matcher.find()){
                    image = matcher.group(1);
                }
                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element, "title");
                link = parser.getValue(element, "link");
                papers.add(new ReadPaper(title, link,image));
            }
            readPaperAdapter = new ReadPaperAdapter(MainActivity.this,android.R.layout.simple_list_item_1, papers);
            MainActivity.this.listView.setAdapter(readPaperAdapter);
            super.onPostExecute(s);
        }
    }
}
