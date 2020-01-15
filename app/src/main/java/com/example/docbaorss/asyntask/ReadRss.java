package com.example.docbaorss.asyntask;

import android.os.AsyncTask;
import com.example.docbaorss.model.Paper;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class ReadRss extends AsyncTask<String, Void, ArrayList<Paper>> {
    private AsynListener asynListener;
    private ArrayList<Paper> papers = new ArrayList<>();

    public ReadRss() {

    }

    public void getDataFromUrl(String url) {
        this.execute(url);
    }

    public interface AsynListener
    {
        void OnSuccess(ArrayList<Paper> listPaper);
    }

    public void setAsynListener(AsynListener asynListener) {
        this.asynListener = asynListener;
    }


    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Paper> doInBackground(String... strings) {
        papers = new ArrayList<>();
        try {
            org.jsoup.nodes.Document document = Jsoup.connect(strings[0]).get();
            Elements elements = document.select("item");
            Paper arrPaper;

            for (org.jsoup.nodes.Element element : elements) {
                arrPaper = new Paper();
                arrPaper.setTitle(element.select("title").text());
                arrPaper.setImage(Jsoup.parse(element.select("description").text()).select("img").attr("src"));
                arrPaper.setLink(element.select("link").text());
                arrPaper.setPubDate(element.select("pubdate").text());
                arrPaper.setDetails(element.select("description").text().substring(element.select("description").text().indexOf("<br />")+6));

                papers.add(arrPaper);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return papers;
    }

    @Override
    protected void onPostExecute(ArrayList<Paper> s) {
        super.onPostExecute(s);
        asynListener.OnSuccess(papers);
    }
}
