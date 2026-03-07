package apiEngine.client;

import listeners.RestLoggerFilter;
import apiEngine.interfaces.BuilderInterface;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

public class CallApi {
    BuilderInterface requestBuilder;
    RequestSpecification request;

    public CallApi(BuilderInterface requestBuilder) {
        this.requestBuilder = requestBuilder;
        request = requestBuilder.buildRequest().filter(new RestLoggerFilter());
    }

    public BuilderInterface getBuilderRequest() {
        return this.requestBuilder;
    }

    public Response requestGet(String endPoint) {
        return
                        request
                        .when()
                        .get(endPoint);
    }

    public Response requestPost(String endPoint, JSONObject body) {
                return
                        request
                                .body(body.toString())
                        .when()
                        .post();
    }
}
