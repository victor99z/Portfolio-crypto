package database;

public class CoinInfo {
    public String idCoin;
    public double buyPrice;
    public double qtd;

    public CoinInfo(String idCoin,double buyPrice,double qtd){
        this.idCoin=idCoin;
        this.buyPrice=buyPrice;
        this.qtd=qtd;
    }

    @Override
    public String toString() {
        return "CoinInfo{" +
                "idCoin='" + idCoin + '\'' +
                ", buyPrice=" + buyPrice +
                ", qtd=" + qtd +
                '}';
    }
}
