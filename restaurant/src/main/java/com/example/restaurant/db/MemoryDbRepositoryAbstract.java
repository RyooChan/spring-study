package com.example.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T> { // 이곳에서 implemets하여 저 값을 만들어줄 수 있다.

    private final List<T> db = new ArrayList<>();
    private int index = 0;      // DB에서 PK를 자동으로 auto generate해주는 index

    @Override
    public Optional<T> findById(int index) {    // 이 index를 사용하려면, DB에서 사용할 Entity가 필요하다. MemoryDbEntity에서 protected로 Index를 정의하고, 다시 여기서 extends하여 받아와서 사용한다.
        db.stream().filter(it -> it.getIndex() == index).findFirst();
        return db.stream().filter(it -> it.getIndex() == index).findFirst(); // List db에서값을 받아오는데, extends를 통해 MemoryDbEntity로 넣은 index를 getIndex를 사용하여 받아온다. Opional을 사용하여 있으면 받아오고 없으면 받아오지 않도록.
    }

    @Override
    public T save(T entity) {
        // 이 optional에 대해 값이 있는지 없는지 해당 index를 통해 찾아보기
        var optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        if(optionalEntity.isEmpty()){
            // db 에 데이터가 없는 경우
            // insert
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;

        }else{
            // db 에 이미 데이터가 있는 경우
            // update
            var preIndex = optionalEntity.get().getIndex();
            entity.setIndex(preIndex);

            deleteById(preIndex);       // 기존 데이터를 지우고 새로 insert한다고 생각하면 된다.
            db.add(entity);
            return entity;
        }
    }

    @Override
    public void deleteById(int index) {
        // 해당 index를 검색해서 삭제하는 것이다.
        var optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();
        if(optionalEntity.isPresent()){
            db.remove(optionalEntity.get());
        }
    }

    @Override
    public List<T> findAll() {
        return db;
    }   // 싹다 검색하기
}
