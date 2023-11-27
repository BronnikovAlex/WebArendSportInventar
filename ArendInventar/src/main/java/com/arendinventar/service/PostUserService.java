package com.arendinventar.service;

import com.arendinventar.model.PostUser;
import com.arendinventar.repository.PostUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostUserService {

    private final PostUserRepository postUserRepository;

    @Autowired
    public PostUserService(PostUserRepository postUserRepository) {
        this.postUserRepository = postUserRepository;
    }

    public List<PostUser> getAllPosts() {
        return postUserRepository.findAll();
    }

    public Optional<PostUser> getPostById(Long id) {
        return postUserRepository.findById(id);
    }

    public PostUser savePost(PostUser postUser) {
        return postUserRepository.save(postUser);
    }

    public void deletePost(Long id) {
        postUserRepository.deleteById(id);
    }

}
