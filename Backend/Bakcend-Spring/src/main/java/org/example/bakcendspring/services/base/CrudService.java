package org.example.bakcendspring.services.base;

public interface CrudService<ID, Request, Response, Filter> {
    Response getById(ID id);
    void create(Request request);
    void update(Request request, ID id);
    void delete(ID id);
}

