package com.arun.health.service;

import com.arun.health.dto.PostDto;
import com.arun.health.entities.PostEntity;
import com.arun.health.exceptions.ResourceNotFoundException;
import com.arun.health.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    public final PostRepository postRepository;
    public final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts() {

        return postRepository
                .findAll()
                .stream()
                .map(posts -> modelMapper.map(posts, PostDto.class))
                .toList();
    }


    @Override
    public PostDto getPostById(Long id) {
        return postRepository
                .findById(id)
                .map(post -> modelMapper.map(post, PostDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id)); // Replace with a custom exception if needed
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        PostEntity postEntity = modelMapper.map(postDto, PostEntity.class);
        postRepository.save(postEntity);
        return modelMapper.map(postEntity, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        PostEntity olderPost = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postDto.getId()));
        olderPost.setTitle(postDto.getTitle());
        olderPost.setDescription(postDto.getDescription());
//        modelMapper.map(postDto, olderPost);

        PostEntity updatedPost = postRepository.save(olderPost);
        return modelMapper.map(updatedPost, PostDto.class);
    }


    public PostEntity updatePost(PostEntity post, Long postId) {
        // Fetch the existing entity by ID
        PostEntity existingPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        // Copy non-ID fields from the input entity
        existingPost.setTitle(post.getTitle());
        existingPost.setDescription(post.getDescription());
        // Add more fields as needed

        return postRepository.save(existingPost); // Save updated entity
    }


}
