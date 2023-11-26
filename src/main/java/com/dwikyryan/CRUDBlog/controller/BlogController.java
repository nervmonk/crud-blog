package com.dwikyryan.CRUDBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dwikyryan.CRUDBlog.model.Blog;
import com.dwikyryan.CRUDBlog.repository.BlogRepository;
import com.dwikyryan.CRUDBlog.service.BlogService;

@RestController
public class BlogController {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    BlogService blogService;

    @PostMapping("/addBlog")
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog) {
        return blogService.addBlog(blog);
    }

    @GetMapping("/getBlogById/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        return blogService.getBlogById(id);
    }

    @GetMapping("/getAllBlogs")
    public ResponseEntity<Page<Blog>> getAllBlog(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        return blogService.getAllBlog(page, size, sortBy, sortOrder);
    }

    @PutMapping("/updateBlogById/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog updatedBlog) {
        return blogService.updateBlog(id, updatedBlog);
    }

    @DeleteMapping("/deleteBlogById/{id}")
    public ResponseEntity<HttpStatus> deleteBlogById(@PathVariable Long id) {
        return blogService.deleteBlogById(id);
    }
}
