package com.example.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.practice.entity.UserHistory;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Integer> {

}