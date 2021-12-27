package com.example.restaurant.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {
    Optional<T> findById(int index);        // 값을 찾아서 return하는 메소드
    T save(T entity);                       // 저장하는 메소드
    void deleteById(int index);             // 삭제하는 메소드
    List<T> findAll();                      // 전체 값을 찾아오는 findAll 메소드
    // 이 메소드들은 MemoryDbRepositoryAbstract에 추상 클래스로 설정해주었다. 나중에 JPARepository를 extends 하면 저걸 실제로 DB에 저장 가능할듯.
}
