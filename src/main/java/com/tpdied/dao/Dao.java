package com.tpdied.dao;

import java.util.List;
import java.util.Optional;

import com.tpdied.models.Eliminable;

public interface Dao<T extends Eliminable> {

	Optional<T> getById(int id);

	List<T> getAll();

	void save(T t);

	void update(T t);

	void delete(T t);
}