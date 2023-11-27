package com.arendinventar.controller;

import com.arendinventar.model.PostUser;
import com.arendinventar.service.PostUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post-users")
public class PostUserController {

    private final PostUserService postUserService;

    @Autowired
    public PostUserController(PostUserService postUserService) {
        this.postUserService = postUserService;
    }

    @GetMapping
    public List<PostUser> getAllPosts() {
        return postUserService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostUser> getPostById(@PathVariable Long id) {
        return postUserService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PostUser createPost(@RequestBody PostUser postUser) {
        return postUserService.savePost(postUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostUser> updatePost(@PathVariable Long id, @RequestBody PostUser updatedPost) {
        return postUserService.getPostById(id)
                .map(existingPost -> {
                    existingPost.setNamePost(updatedPost.getNamePost());
                    existingPost.setAuthority(updatedPost.getAuthority());
                    existingPost.setSalary(updatedPost.getSalary());
                    return ResponseEntity.ok(postUserService.savePost(existingPost));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postUserService.deletePost(id);
        return ResponseEntity.noContent().build();
    }


}
