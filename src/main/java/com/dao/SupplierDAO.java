package com.dao;
import com.model.Suppliers;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierDAO extends JpaRepository<Suppliers, Long> {

    @Query("SELECT s FROM Suppliers s WHERE s.name = :name")
    List<Suppliers> findByName(@Param("name") String name);

    @Query("SELECT s FROM Suppliers s WHERE s.address.city = :city")
    List<Suppliers> findByCity(@Param("city") String city);

    @Query("SELECT s FROM Suppliers s WHERE s.address.state = :state")
    List<Suppliers> findByState(@Param("state") String state);
}
