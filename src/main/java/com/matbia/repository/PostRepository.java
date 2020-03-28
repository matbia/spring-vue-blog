package com.matbia.repository;

import com.matbia.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByOrderByTimestampDesc(Pageable pageable);
}
