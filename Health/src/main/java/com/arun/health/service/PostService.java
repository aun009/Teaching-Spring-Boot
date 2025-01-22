package com.arun.health.service;

import com.arun.health.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<PostDto> getAllPosts();
    PostDto getPostById(Long id);
    PostDto createPost(PostDto postDto);

    PostDto updatePost(PostDto postDto, Long postId);
}
