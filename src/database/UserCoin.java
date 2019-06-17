package database;

import api.DataExchange;

import java.util.ArrayList;

public class UserCoin {
    public String username;
    public ArrayList<CoinInfo> arrayCoins;

    public UserCoin(){
        this.username="";
        arrayCoins=new ArrayList<>();
    }

    @Override
    public String toString() {
        return "UserCoin{" +
                "username='" + username + '\'' +
                ", ArrayCoins=" + arrayCoins +
                '}';
    }

    public CoinInfo getCoinBySymbol(String id){
        for(CoinInfo ci:arrayCoins){
            if(ci.idCoin.equals(id)){
                return ci;
            }
        }
        return null;
    }

    public void addCoin(CoinInfo addCoin){
        for(CoinInfo ci:arrayCoins){
            if(ci.idCoin.equals(addCoin.idCoin)){
                ci.qtd+=addCoin.qtd;
                ci.buyPrice = (ci.buyPrice + addCoin.buyPrice)/2;
                return;
            }
        }
        arrayCoins.add(addCoin);
    }

    public boolean sellCoin(CoinInfo sellCoin, ArrayList<DataExchange> data) {
        for(CoinInfo ci:arrayCoins){
            if(ci.idCoin.equals(sellCoin.idCoin)){
                if(sellCoin.qtd <= ci.qtd){
                    for(DataExchange de:data){
                        if(de.baseSymbol.equals(sellCoin.idCoin)){
                            for(DataExchange dol:data){
                                if(dol.baseSymbol.equals("TUSD")){
                                    ci.qtd-=sellCoin.qtd;
                                    CoinInfo adc = new CoinInfo("TUSD",Double.valueOf(dol.priceUsd),sellCoin.qtd*Double.valueOf(de.priceUsd));
                                    addCoin(adc);
                                    if(ci.qtd ==0)
                                        try{
                                            deleteCoin(ci);
                                            return true;
                                        }catch (Exception e){
                                            System.err.println("Coin not removed!!");
                                            return false;
                                        }
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean withdrawCoin(CoinInfo wdCoin, ArrayList<DataExchange> data){
        for(CoinInfo ci:arrayCoins){
            if(ci.idCoin.equals(wdCoin.idCoin)){
                if(wdCoin.qtd <= ci.qtd){
                        for(DataExchange dol:data){
                            if(dol.baseSymbol.equals("TUSD")){
                                ci.qtd-= wdCoin.qtd*Double.valueOf(dol.priceUsd);
                                System.out.println(ci.qtd);
                                if(ci.qtd <= 0 ) {
                                    if(deleteCoin(ci))
                                        return true;
                                    else{
                                        System.err.println("Coin not removed!!");return false;
                                    }
                                }else{
                                    return true;
                                }
                            }
                        }
                }
            }
        }
        System.out.println("FML");
        return false;
    }

    public boolean deleteCoin(CoinInfo ci) {
        for(int c=0;c < arrayCoins.size();c++){
            if(arrayCoins.get(c).idCoin.equals(ci.idCoin)){
                arrayCoins.remove(c);
                System.out.println("Coin removed");
                return true;
            }
        }
        return false;
    }
}
