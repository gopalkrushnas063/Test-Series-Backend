package com.example.Test.Series.repositories;

import com.example.Test.Series.entity.Submenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmenuRepository extends JpaRepository<Submenu, Long> {
}
