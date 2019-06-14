package database;

import java.util.ArrayList;

public class UserCoin {
    public String username;
    public ArrayList<CoinInfo> ArrayCoins;

    @Override
    public String toString() {
        return "UserCoin{" +
                "username='" + username + '\'' +
                ", ArrayCoins=" + ArrayCoins +
                '}';
    }
}
