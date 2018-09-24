package com.otn.reactive.reactivespringpartI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyExtractors.toMono;

@Component
public class ClienteHandler {

    @Autowired
    MockClienteRepository mockClienteRepository;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<Cliente> clientes = Flux.fromIterable(mockClienteRepository.getAll());
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(clientes, Cliente.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Cliente> clienteMono = request.bodyToMono(Cliente.class);
        return ServerResponse.ok().build(mockClienteRepository.save(clienteMono));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String clienteId = request.pathVariable("id");
        Mono<Cliente> clienteMono = request.bodyToMono(Cliente.class);
        return ServerResponse.ok().build();

//        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
//        Mono<Cliente> personMono = this.mockClienteRepository.update(clienteMono.blockOptional().get());
//        return personMono
//                .then(person -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(person)))
//                .otherwiseIfEmpty(notFound);
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String clienteId = request.pathVariable("id");
        return ServerResponse.ok().build();
    }
}
