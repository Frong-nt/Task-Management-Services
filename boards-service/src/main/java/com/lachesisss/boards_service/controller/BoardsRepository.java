package com.lachesisss.boards_service.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lachesisss.boards_service.models.Boards;

@Repository
public interface BoardsRepository extends JpaRepository<Boards, String> {

}
