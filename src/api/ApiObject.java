package api;

import com.google.gson.Gson;
import javafx.application.Platform;

public class ApiObject {


    public ApiObject(){
        setUrl("https://api.coincap.io/v2/markets?exchangeId=binance&quoteId=tether&limit=50");
        t1.start();
    }


    protected DataArray dataArray;
    protected String url;
    private boolean connectionIssues=false;
    private boolean threadStop = false;
    private boolean dataReady = false;

    public boolean isDataReady() { return dataReady; }

    public void getResponseFromAPI(String url) throws Exception{
        try {
            ApiReader response = new ApiReader(url);
            Gson gson = new Gson();
            this.dataArray = gson.fromJson(response.toString(), DataArray.class);
        }catch (Exception e){
            throw e;
        }
    }

    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return url;
    }

    public DataArray getDataArray() {
        if(isDataReady()){
            return dataArray;
        }else{
            return null;
        }
    }

    public void threadShutdown(){ threadStop = true; }
    public void threadStart(){ threadStop = false;t1.start(); }




    Thread t1 = new Thread(){
        @Override
        public void run() {
            while(!threadStop){
                try {
                    getResponseFromAPI(getUrl());
                    connectionIssues=false;
                    dataReady=true;
                    sleep(8000);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    System.err.println("Parsing Error!");
                }catch (Exception e){
                    System.err.println("Connection Failed!> "+e.toString() );
                    if(!connectionIssues){
                        connectionIssues=true;
                        dataReady=false;
                    }
                    try {
                        sleep(8000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    };
}
