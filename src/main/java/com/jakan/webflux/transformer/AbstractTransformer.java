package com.jakan.webflux.transformer;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractTransformer<E,D> {
    public abstract E toEntity(D dto);
    public abstract D toDto(E entity);

}
