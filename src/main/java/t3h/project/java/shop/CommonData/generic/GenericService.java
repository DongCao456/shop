package t3h.project.java.shop.CommonData.generic;

import t3h.project.java.shop.CommonData.model.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface GenericService<T extends AbstractEntity,ID>{


    List<T> findAll();
    Optional<T> findById(ID id);

    T save(T entity);
    T update(T entity);

    void deleteById(ID id);

}
