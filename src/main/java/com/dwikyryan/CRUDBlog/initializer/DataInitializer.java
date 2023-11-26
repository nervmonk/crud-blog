package com.dwikyryan.CRUDBlog.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dwikyryan.CRUDBlog.model.Blog;
import com.dwikyryan.CRUDBlog.repository.BlogRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {
    @Autowired
    BlogRepository blogRepository;

    @PostConstruct
    public void init() {
        for (int i = 1; i < 36; i++) {
            Blog blog = Blog.builder()
                    .title("The " + i + " blog")
                    .body("Our " + i + " blog content")
                    .author("Ryan Dwiky " + i)
                    .build();
            blogRepository.save(blog);
        }
    }
}
