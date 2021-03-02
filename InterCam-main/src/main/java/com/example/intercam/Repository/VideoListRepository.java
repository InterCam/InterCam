package com.example.intercam.Repository;

import com.example.intercam.entity.VideoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoListRepository extends JpaRepository<VideoList, Long> {
    //List<VideoList> findAll (Sort sort);
    Page<VideoList> findAll(Pageable pageable);
}
