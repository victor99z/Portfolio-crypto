package com.api;

public class MainTestApi {
    public static void main(String[] args) {
        ApiReader response1 = new ApiReader("https://api.coincap.io/v2/markets/?exchangeId=binance&baseSymbol=TUSD");
        System.out.println(response1.toString());
    }
}
