package apiEngine.builder;

import apiEngine.interfaces.AccessTokenInterface;

public class AccessToken implements AccessTokenInterface {
    String tokenType;
    AccessToken(String tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public String getToken() {
        if (tokenType == null) {
            return null;
        }
        return "";
    }

    @Override
    public void callApiGetToken() {

    }
}
