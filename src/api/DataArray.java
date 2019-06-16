package api;

import java.util.ArrayList;

public class DataArray {
    public long timestamp;
    public ArrayList<DataExchange> data;
    public DataArray(){
        data=new ArrayList<>();
    }
}
