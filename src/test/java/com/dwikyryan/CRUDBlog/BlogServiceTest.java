package com.dwikyryan.CRUDBlog;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dwikyryan.CRUDBlog.model.Blog;
import com.dwikyryan.CRUDBlog.repository.BlogRepository;
import com.dwikyryan.CRUDBlog.service.BlogService;

@RunWith(MockitoJUnitRunner.class)
public class BlogServiceTest {
    @Mock
    BlogRepository blogRepository;

    @InjectMocks
    BlogService blogService;

    @Test
    public void testAddBlog() {
        Blog blog = Blog.builder().title("Test Service Title").body("Test Service Body").author("Test Service Author")
                .build();
        ResponseEntity<Blog> response = blogService.addBlog(blog);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateBlog() {
        Long blogId = 1L;
        Blog existingBlog = Blog.builder().title("Test Update Blog").body("Test Update Blog").author("Test Update Blog")
                .build();
        Blog updatedBlog = Blog.builder().title("Test Update Blog Updated").body("Test Update Blog Updated")
                .author("Test Update Blog Updated").build();

        Mockito.when(blogRepository.findById(blogId)).thenReturn(Optional.of(existingBlog));
        Mockito.when(blogRepository.save(Mockito.any())).thenReturn(updatedBlog);

        ResponseEntity<Blog> response = blogService.updateBlog(blogId, updatedBlog);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
