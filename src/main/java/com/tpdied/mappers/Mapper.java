package com.tpdied.mappers;

public interface Mapper<T, D>{
    
    public T toEntity(D dto);
    public D toDto(T entity);

}
