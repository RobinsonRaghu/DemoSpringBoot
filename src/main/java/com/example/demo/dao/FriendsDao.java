package com.example.demo.dao;

import com.example.demo.models.FriendsTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendsDao extends JpaRepository<FriendsTable, Integer> {
}
