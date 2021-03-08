package com.example.intercam.Repository;

import com.example.intercam.entity.VideoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoListRepository extends JpaRepository<VideoList, Long> {
    Page<VideoList>  findAll(Pageable pageable);
    Optional<VideoList> findAllByUserId(Long UserId);
}
