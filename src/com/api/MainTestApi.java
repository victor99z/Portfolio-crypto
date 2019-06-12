package com.api;

import com.google.gson.*;

public class MainTestApi extends Thread{
    private String url;

    public MainTestApi(String url){
        this.url = url;
        t1.start();
    }

    Thread t1 = new Thread(){
        @Override
        public void run() {
            while(true){
                try {

                    ApiReader response = new ApiReader(url); // Faz a chamada da API, recebe uma String no formato JSON
                    Gson gson = new Gson(); // Cria o objeto json para manipularmos a String
                    DataArray dataarray = gson.fromJson(response.toString(),DataArray.class); //

                    for(DataExchange sc: dataarray.data){
                        System.out.printf("[%s/%s] : %s\n",sc.baseSymbol,sc.quoteSymbol,sc.priceQuote);
                    }

                    sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public static void main(String[] args) {
        MainTestApi response1 = new MainTestApi("https://api.coincap.io/v2/markets/?exchangeId=binance&limit=2");
    }
}
