package database;

public class CoinInfo {
    public String idCoin;
    public Double buyPrice;
    public double qtd;

    @Override
    public String toString() {
        return "CoinInfo{" +
                "idCoin='" + idCoin + '\'' +
                ", buyPrice=" + buyPrice +
                ", qtd=" + qtd +
                '}';
    }
}
