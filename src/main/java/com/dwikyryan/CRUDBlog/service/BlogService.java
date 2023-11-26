package com.dwikyryan.CRUDBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dwikyryan.CRUDBlog.model.Blog;
import com.dwikyryan.CRUDBlog.repository.BlogRepository;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    public ResponseEntity<Blog> addBlog(Blog blog) {
        try {
            Blog savedBlog = blogRepository.save(blog);
            return ResponseEntity.ok(savedBlog);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Blog> getBlogById(Long id) {
        var blog = blogRepository.findById(id);
        if (blog.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(blog.get());
        }
    }

    public ResponseEntity<Page<Blog>> getAllBlog(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        var blogs = blogRepository.findAll(pageable);

        if (blogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(blogs);
    }

    public ResponseEntity<Blog> updateBlog(Long id, Blog updatedBlog) {
        var isExist = blogRepository.findById(id);
        if (isExist.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var existingBlog = isExist.get();
        existingBlog.setTitle(updatedBlog.getTitle());
        existingBlog.setBody(updatedBlog.getBody());
        existingBlog.setAuthor(updatedBlog.getAuthor());

        Blog updated = blogRepository.save(existingBlog);
        return ResponseEntity.ok(updated);
    }

    public ResponseEntity<HttpStatus> deleteBlogById(Long id) {
        var isExist = blogRepository.findById(id);
        if (isExist.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        blogRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
