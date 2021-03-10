package com.example.intercam.Repository;

import com.example.intercam.entity.VideoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoListRepository extends JpaRepository<VideoList, Long> {
    Optional<VideoList> findAllByUserId(Long UserId);
}
