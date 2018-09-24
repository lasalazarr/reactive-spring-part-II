package com.otn.reactive.reactivespringpartI;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MockClienteRepository {

    private Map<String, Cliente> clientesStore = new HashMap<String, Cliente>();

    @PostConstruct
    public void initIt() throws Exception {
        clientesStore.put("1001", new Cliente("1001", "Alberto", "Salazar", 3299999, "Quito, EC170102"));
        clientesStore.put("1002", new Cliente("1002", "Pablo", "Arizaga", 22332233, "Quito, EC170104"));
        clientesStore.put("1003", new Cliente("1003", "Maria", "Valdez", 82923233, "Quito, EC178976"));
        clientesStore.put("1004", new Cliente("1004", "Paola", "Llanos", 87878799, "Quito, EC179087"));
        clientesStore.put("1005", new Cliente("1005", "Tamara", "Cisneros", 4356772, "Quito, EC876590"));
    }

    public Collection<Cliente> getAll() {
        return clientesStore.values();
    }

    public Mono<Void> save(Mono<Cliente> cliente){
        return cliente.doOnNext(c -> {
            String id = c.getId();
            clientesStore.put(id, c);
        }).thenEmpty(Mono.empty());
    }

    public Mono<Void> update(Mono<Cliente> cliente){
        return cliente.doOnNext(c -> {
            String id = c.getId();
            clientesStore.put(id, c);
        }).thenEmpty(Mono.empty());
    }

    public void delete(String id){
        clientesStore.remove(id);
    }
}