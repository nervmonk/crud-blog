package com.dwikyryan.CRUDBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dwikyryan.CRUDBlog.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

}
