package ru.sf.ibapi.custommapper;

import java.util.List;

public interface CustomMapper {
    <S, T> T map(S source, Class<T> targetClass);
    <S, T> List<T> mapList(List<S> source, Class<T> targetClass);
}
