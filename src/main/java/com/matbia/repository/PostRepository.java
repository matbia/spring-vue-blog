package com.matbia.repository;

import com.matbia.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT id, timestamp, title, CONCAT(SUBSTRING(body, 1, 512), '...') AS body FROM posts ORDER BY timestamp DESC LIMIT 5 OFFSET ?1", nativeQuery = true)
    List<Post> limit(int offset);
}
