package apiEngine.interfaces;

import apiEngine.builder.AccessToken;
import apiEngine.builder.CoreRequestBuilder;
import io.restassured.specification.RequestSpecification;

public interface BuilderInterface {
    RequestSpecification buildRequest();
    AccessToken getToken();
    CoreRequestBuilder setAccessToken(String tokenType);
    CoreRequestBuilder setUrl(String url);
    String getUrl();
}
