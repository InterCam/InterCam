package com.example.intercam.Repository;

import com.example.intercam.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentService extends JpaRepository<Comment, Long> {
}
