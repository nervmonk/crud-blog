package com.dwikyryan.CRUDBlog;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dwikyryan.CRUDBlog.controller.BlogController;
import com.dwikyryan.CRUDBlog.model.Blog;
import com.dwikyryan.CRUDBlog.service.BlogService;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(BlogController.class)
public class BlogControllerTest {
    @Mock
    BlogService blogService;

    @InjectMocks
    BlogController blogController;

    @Test
    public void testAddBlog() {
        Blog blog = Blog.builder().title("Testing Add Blog").body("Testing Add Body").author("Testing Add Author")
                .build();
        ResponseEntity<Blog> expected = ResponseEntity.ok(blog);
        Mockito.when(blogService.addBlog(Mockito.any())).thenReturn(expected);
        ResponseEntity<Blog> actual = blogController.addBlog(blog);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void testUpdateBlog() {
        Long id = 1L;
        Blog updatedBlog = Blog.builder().title("Testing Updated Title").body("Testing Updated Body")
                .author("Testing Updated Author").build();
        var expected = ResponseEntity.ok(updatedBlog);

        Mockito.when(blogService.updateBlog(id, updatedBlog)).thenReturn(expected);

        var actual = blogController.updateBlog(id, updatedBlog);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }
}
