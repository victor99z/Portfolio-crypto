package database;

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
}
