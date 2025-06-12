package com.postservice.services;

import com.postservice.models.Post;

import java.util.List;

public interface PostService {

    Post save(Post post);

    List<Post> findAll();

    void deleteById(int id);


}
