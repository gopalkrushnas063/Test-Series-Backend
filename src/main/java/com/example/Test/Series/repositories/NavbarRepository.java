package com.example.Test.Series.repositories;

import com.example.Test.Series.entity.Navbar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NavbarRepository extends JpaRepository<Navbar, Integer> {
}
