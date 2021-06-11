package com.qa.hobby.utils;

public interface Mapper<E, D> {

	D mapToDTO(E entity);

	E mapFromDTO(D dto);
}