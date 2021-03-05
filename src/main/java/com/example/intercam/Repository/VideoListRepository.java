package com.example.intercam.Repository;

import com.example.intercam.entity.VideoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoListRepository extends JpaRepository<VideoList, Long> {
    Page<VideoList>  findAll(Pageable pageable);
}
