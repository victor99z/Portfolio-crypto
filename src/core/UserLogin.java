package core;

import com.google.gson.Gson;

import java.io.*;

public class UserLogin {

    //substituir a gambiarra por um servidor de verdade

    private boolean loginState=false;

    public UserLogin(){setUserLogin();}

    public boolean getLoginState(){
        return loginState;
    }

    private UserTypeObject gs;

    public UserTypeObject getGs() { return gs; }

    public void setUserLogin() {
        this.gs = new UserTypeObject();
        readJSON();
    }

    public boolean checkLoginMatches(String UserName, String Password){
        loginState=false;
        if(gs != null){
            try{
                //System.out.println( UserName+ " : "+gs.getUsername()+" | "+Password+" : "+gs.getPassword());
                if(UserName.equals(gs.getUsername()) && Password.equals(gs.getPassword())){
                    loginState = true;
                }
            }catch (Exception e){
                System.err.println("Failure");
            }
        }
        return getLoginState();
    }

    public boolean readJSON(){
        Gson g = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\login_info.json"));
            gs = g.fromJson(br, UserTypeObject.class);
            br.close();
            return true;
        } catch (IOException e) {
            //e.printStackTrace();
            System.err.println("File not found?");
            gs = null;
            return false;
        }
    }

    public boolean writeJSON(String email, String UserName, String Password){
        Gson g = new Gson();
        try {
            //BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"login_info.json"));
            //DataArray obj = g.fromJson(br, DataArray.class);
            //System.out.println(obj);
            UserTypeObject n = new UserTypeObject(UserName,email,Password);
            String json = g.toJson(n);

            FileWriter write = new FileWriter(System.getProperty("user.dir")+"\\login_info.json");
            write.write(json);
            write.close();
            readJSON();
            return true;
        } catch (IOException e) {
            System.err.println("File not written?");
            //e.printStackTrace();
            readJSON();
            return false;
        }
    }
}
