package com.postservice.services.Impl;

import com.postservice.models.Post;
import com.postservice.repository.PostRepository;
import com.postservice.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;


    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }



    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        postRepository.deleteById((long) id);
    }

}
