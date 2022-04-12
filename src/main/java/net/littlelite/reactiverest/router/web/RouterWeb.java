/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.reactiverest.router.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class RouterWeb
{
    @Bean
    public RouterFunction<ServerResponse> htmlRouter(
            @Value("classpath:/assets/index.html") Resource html)
    {
        return route(
                GET("/"),
                request -> ok()
                        .contentType(MediaType.TEXT_HTML).bodyValue(html)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> imgRouter()
    {
        return RouterFunctions.resources("/assets/img/**",
                new ClassPathResource("assets/img/"));
    }

    @Bean
    public RouterFunction<ServerResponse> javascriptRouter()
    {
        return RouterFunctions.resources("/assets/js/**",
                new ClassPathResource("assets/js/"));
    }

    @Bean
    public RouterFunction<ServerResponse> cssRouter()
    {
        return RouterFunctions.resources("/assets/css/**",
                new ClassPathResource("assets/css/"));
    }


}
