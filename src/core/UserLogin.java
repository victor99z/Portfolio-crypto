package core;

public class UserLogin {

    //substituir a gambiarra por um servidor de verdade

    private boolean loginState;

    public boolean getLoginState(){
        return loginState;
    }

    public UserLogin(String UserName, String Password){
        if(UserName.equals("admin") && Password.equals("admin")){
            loginState = true;
        }else{
            loginState = false;
        }
    }
}
