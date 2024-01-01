package edu.scau.common.config;


import edu.scau.common.code.*;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        List<Response> responseList = new ArrayList<>();
        Arrays.stream(ResponseCode.values()).forEach(resultCode -> {
            responseList.add(
                    new ResponseBuilder().code(resultCode.getCode().toString()).description(resultCode.getMsg()).build()
            );
        });
        addAllResponses(responseList, UserErrorCode.values());
        addAllResponses(responseList, UnexpectedErrorCode.values());
        addAllResponses(responseList, SystemErrorCode.values());
        RequestParameterBuilder tokenPar = new RequestParameterBuilder();
        List<RequestParameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("授权token").in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))).required(true).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.OAS_30)
                .globalResponses(HttpMethod.GET, responseList)
                .globalResponses(HttpMethod.POST, responseList)
                .globalResponses(HttpMethod.PUT, responseList)
                .globalResponses(HttpMethod.DELETE, responseList)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.scau.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(pars);
    }

    private void addAllResponses(List<Response> responseList, ErrorCode[] errorCodes) {
        Arrays.stream(errorCodes).forEach(errorCode -> responseList.add(
                new ResponseBuilder()
                        .code(String.valueOf(errorCode.getCode()))
                        .description(errorCode.getMsg())
                        .build()
        ));
    }

    @SuppressWarnings("all")
    public ApiInfo apiInfo() {
        return new ApiInfo(
                "API",
                "API文档",
                "v1.0",
                "42.193.127.135",
                "null", //开发者团队的名字
                "null",
                "null"
        );
    }


    /**
     * 解决springboot升级到2.6.x之后，knife4j报错
     *
     * @param webEndpointsSupplier        the web endpoints supplier
     * @param servletEndpointsSupplier    the servlet endpoints supplier
     * @param controllerEndpointsSupplier the controller endpoints supplier
     * @param endpointMediaTypes          the endpoint media types
     * @param corsProperties              the cors properties
     * @param webEndpointProperties       the web endpoint properties
     * @param environment                 the environment
     * @return the web mvc endpoint handler mapping
     */
    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(
            WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier,
            ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes,
            CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties,
            Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList<>();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = shouldRegisterLinksMapping(webEndpointProperties,
                environment, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes,
                corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath),
                shouldRegisterLinksMapping, null);
    }

    /**
     * shouldRegisterLinksMapping
     *
     * @param webEndpointProperties webEndpointProperties
     * @param environment           environment
     * @param basePath              /
     * @return boolean
     */
    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties,
                                               Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath)
                || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }
}
