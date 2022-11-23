package com.project.blogapi.blogapi.repository;


import java.util.List;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.project.blogapi.blogapi.entities.Category;
import com.project.blogapi.blogapi.entities.Post;
import com.project.blogapi.blogapi.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer>{
   List<Post> findAllByUser(User user);
   List<Post> findByCategory(Category category); 
   @Query("select p from Post p where p.title like %:key%" )
    List<Post> searchByTitle( String key);
}
