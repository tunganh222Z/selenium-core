package listeners;

import core.CoreManager;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestLoggerFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(RestLoggerFilter.class);
    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification,
                           FilterableResponseSpecification filterableResponseSpecification,
                           FilterContext filterContext) {
        logger.info("========== SEND REQUEST ==========");
        logger.info("==========> Method : " + filterableRequestSpecification.getMethod());
        logger.info("==========> URI : " + filterableRequestSpecification.getURI());
        Object req = filterableRequestSpecification.getBody();
        String reqBody = "";
        String stepName = "Call API: " + filterableRequestSpecification.getURI() + " | METHOD: " + filterableRequestSpecification.getMethod();
        if (req != null) {
            logger.info("Request Body: \n" + req.toString());
            reqBody = req.toString();
        }

        Response res = filterContext.next(filterableRequestSpecification, filterableResponseSpecification);

        CoreManager.getContext().getReportListener().onApiStep(
                stepName,
                reqBody,
                res.getBody().asPrettyString()
        );

        logger.info("==========> RESPONSE : " + res.getBody().prettyPrint());

        return res;
    }
}
