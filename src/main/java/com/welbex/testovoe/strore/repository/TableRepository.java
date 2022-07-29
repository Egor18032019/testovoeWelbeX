package com.welbex.testovoe.strore.repository;

import com.welbex.testovoe.strore.entities.TableEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface TableRepository extends JpaRepository<TableEntities, Long> {
    int deleteTableEntitiesById(Long id);
//    @Transactional
//    @Modifying
//    @Query("DELETE FROM TableEntities u WHERE u.id=:id")
//    int deleteByName(@Param("id") Long id);

}
