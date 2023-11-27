package com.arendinventar.repository;


import com.arendinventar.model.OrderArend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderArendRepository extends JpaRepository<OrderArend, Long> {
    @Query("SELECT o FROM OrderArend o WHERE o.userArSpIn.idUserArSpIn = :idUserArSpIn")
    List<OrderArend> findByUserArSpInIdUserArSpIn(@Param("idUserArSpIn") Long id);

}