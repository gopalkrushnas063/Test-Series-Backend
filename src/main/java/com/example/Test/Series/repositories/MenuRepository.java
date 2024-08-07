package com.example.Test.Series.repositories;

import com.example.Test.Series.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
