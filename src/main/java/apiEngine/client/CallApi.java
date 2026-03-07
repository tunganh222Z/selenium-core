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

    }

    public BuilderInterface getBuilderRequest() {
        return this.requestBuilder;
    }

    private RequestSpecification getFreshRequest() {
        return requestBuilder.buildRequest().filter(new RestLoggerFilter());
    }

    public Response requestGet(String endPoint) {
        return
                getFreshRequest()
                        .when()
                        .get(endPoint);
    }

    public Response requestPost(String endPoint, JSONObject body) {
        return
                getFreshRequest()
                        .body(body.toString())
                        .when()
                        .post();
    }
}
