package apiEngine.builder;

import apiEngine.interfaces.BuilderInterface;
import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class CoreRequestBuilder implements BuilderInterface {
    private AccessToken accessToken;
    private String url;

    @Override
    public RequestSpecification buildRequest() {
        RequestSpecification spec = RestAssured.given()
                .baseUri(getUrl())
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        if (this.accessToken != null || this.accessToken.getToken().isBlank()) {
            spec.header("Authorization", "Bearer " + this.accessToken.getToken());
        }
        return spec;
    }

    @Override
    public AccessToken getToken() {
        return accessToken;
    }

    @Override
    public CoreRequestBuilder setAccessToken(String tokenType) {
        if (tokenType != null || tokenType.isBlank()) {
            tokenType = ConfigReader.get("tokenDefault");
            accessToken = new AccessToken(tokenType);
        }
        return this;
    }

    @Override
    public CoreRequestBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
