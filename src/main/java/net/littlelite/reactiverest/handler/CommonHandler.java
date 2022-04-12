/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.reactiverest.handler;

import lombok.RequiredArgsConstructor;
import net.littlelite.reactiverest.ReactRestApplication;
import net.littlelite.reactiverest.dto.VersionDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CommonHandler
{
    public @NotNull Mono<ServerResponse> getVersion(@NotNull ServerRequest request)
    {
        var version = new VersionDTO(ReactRestApplication.VERSION, true);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(version), VersionDTO.class);
    }
}
