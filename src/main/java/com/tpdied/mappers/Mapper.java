package com.tpdied.mappers;

import java.util.List;

public interface Mapper<T, D>{
    
    public T toEntity(D dto);
    public D toDto(T entity);
    public List<T> toEntity(List<D> dto);
    public List<D> toDto(List<T> entity);
}
