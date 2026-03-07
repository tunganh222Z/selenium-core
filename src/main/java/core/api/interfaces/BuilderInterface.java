package core.api.interfaces;

import core.api.builder.AccessToken;
import core.api.builder.CoreRequestBuilder;
import io.restassured.specification.RequestSpecification;

public interface BuilderInterface {
    RequestSpecification buildRequest();
    AccessToken getToken();
    CoreRequestBuilder setAccessToken(String tokenType);
    CoreRequestBuilder setUrl(String url);
    String getUrl();
}
