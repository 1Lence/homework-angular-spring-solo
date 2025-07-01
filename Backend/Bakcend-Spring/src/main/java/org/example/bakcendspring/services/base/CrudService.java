package org.example.bakcendspring.services.base;

import jakarta.persistence.Id;

import java.util.List;

public interface CrudService<ID, Request, Response, Filter> {
    Response getById(ID id);
    List<Response> getAll(Filter filter);
    void create(Request request);
    void update(Request request, ID id);
    void delete(ID id);
}

