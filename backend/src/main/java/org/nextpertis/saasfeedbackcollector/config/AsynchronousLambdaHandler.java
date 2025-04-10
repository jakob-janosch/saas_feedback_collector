package org.nextpertis.saasfeedbackcollector.config;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.nextpertis.saasfeedbackcollector.SaaSFeedbackCollectorApplication;

public class AsynchronousLambdaHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {

    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(SaaSFeedbackCollectorApplication.class);
        } catch (ContainerInitializationException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not initialize Spring Boot application", e);
        }
    }

    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
        System.out.println("BODY: " + awsProxyRequest.getBody());
        System.out.println("PATH: " + awsProxyRequest.getPath());
        System.out.println("METHOD: " + awsProxyRequest.getHttpMethod());
        System.out.println("RESOURCEPATH: " + awsProxyRequest.getRequestContext().getResourcePath());
        System.out.println("ISB64: " + awsProxyRequest.isBase64Encoded());
        System.out.println("HEADERS: " + awsProxyRequest.getHeaders().toString());
        System.out.println("MULTIVALUEHEADERS: " + awsProxyRequest.getMultiValueHeaders().toString());

        return handler.proxy(awsProxyRequest, context);
    }
}