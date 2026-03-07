package apiEngine.client;

import apiEngine.response.ApiResponse;
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

    }

    public BuilderInterface getBuilderRequest() {
        return this.requestBuilder;
    }

    private RequestSpecification getFreshRequest() {
        return requestBuilder.buildRequest().filter(new RestLoggerFilter());
    }

    public ApiResponse requestGet(String endPoint) {
        Response res = getFreshRequest()
                        .when()
                        .get(endPoint);
        return new ApiResponse(res);
    }

    public ApiResponse requestPost(String endPoint, JSONObject body) {
        Response res = getFreshRequest()
                        .body(body.toString())
                        .when()
                        .post();
        return new ApiResponse(res);
    }
}
