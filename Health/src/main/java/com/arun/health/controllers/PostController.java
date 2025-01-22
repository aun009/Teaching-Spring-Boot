package com.arun.health.controllers;

import com.arun.health.dto.PostDto;
import com.arun.health.entities.PostEntity;
import com.arun.health.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    public final PostService postService;

    @GetMapping
    public List<PostDto> getPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }


    @PostMapping
    public PostDto createPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }

    @PutMapping("{postId}")
    public PostDto updatePost(@RequestBody PostDto postDto, @PathVariable Long postId) {
        return postService.updatePost(postDto, postId);
    }
}