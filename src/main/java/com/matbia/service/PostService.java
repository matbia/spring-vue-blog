package com.matbia.service;

import com.matbia.model.Post;
import com.matbia.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    /**
     * Fetches a single entity from the table by its primary key.
     * @param id primary key
     * @return query result wrapped in Optional<T> container
     */
    public Optional<Post> getOne(long id) {
        return repository.findById(id);
    }

    /**
     * Fetches a collection containing 5 or less entities at selected page offset.
     * @param pageId page offset
     * @return list containing posts with trimmed body field
     */
    public List<Post> getPage(int pageId) {
        return repository.limit(pageId * 5 - 5);
    }

    /**
     * Fetches the total number of pages
     * @return number of posts divided by 5 rounded up
     */
    public int getPageCount() {
        return (int) Math.ceil(repository.count() / 5.d);
    }

    /**
     * Saves the specified entity to the database.
     * If a post with such ID already exists, existing record with specified ID is updated, otherwise a new record is added
     * @param post specified object
     * @return True if no exception was thrown, false otherwise.
     */
    public boolean saveOrUpdate(Post post) {
        try {
            repository.save(post);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes the specified record by its primary key
     * @param id primary key
     * @return True if no exception was thrown, false otherwise.
     */
    public boolean delete(long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
           e.printStackTrace();
           return false;
        }
    }
}
