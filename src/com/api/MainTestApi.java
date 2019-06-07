package com.api;

public class MainTestApi extends Thread{

    // Thread para fazer requisicao da api a cada 5 seg.

    public MainTestApi(String url){
        run(url);
    }

    public void run(String url){
        while(true){
            try {
                ApiReader response = new ApiReader(url);
                System.out.println(response);
                sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        MainTestApi response1 = new MainTestApi("https://api.coincap.io/v2/markets/?exchangeId=binance&baseSymbol=TUSD");
    }
}
