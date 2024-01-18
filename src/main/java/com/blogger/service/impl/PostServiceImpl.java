package com.blogger.service.impl;

import com.blogger.entity.Post;
import com.blogger.exception.ResourceNotFoundException;
import com.blogger.payload.PostDto;
import com.blogger.repository.PostRepository;
import com.blogger.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
     //this is the post content before saving

        Post savedPost = postRepo.save(post); //this is after saving
        //whatever is saved in DB that content can be accesses with this savedPost


        PostDto dto = new PostDto();
        dto.setId(savedPost.getId());
        dto.setTitle(savedPost.getTitle());
        dto.setDescription(savedPost.getDescription());
        dto.setContent(savedPost.getContent());

        return dto;

    }

//    @Override
//    public void deletePost(long id) {
//        Optional<Post> byId = postRepo.findById(id);
//        if (byId.isPresent()) {
//            postRepo.deleteById(id);
//        } else {
//            throw new ResourceNotFoundException("Post not found with id:" + id);
//        }
   // } ONe way or second way by using lambda expression


@Override
    public void deletePost(long id) {
    Post post = postRepo.findById(id).orElseThrow(
            ()->new ResourceNotFoundException("Post not found with id: " +id)
               //this becomes like a try block-throws exception
    );

    postRepo.deleteById(id);

}

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);  //we have to convert string to sort otherwise it will give error
        Page<Post> pagePosts = postRepo.findAll(pageable);
         List<Post> posts = pagePosts.getContent();

        //it will return back list  of posts and this should be converted into list of dtos
         List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id" + postId)

        );

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post savedPost = postRepo.save(post);

         PostDto dto = mapToDto(savedPost);

        return dto;

    }
    PostDto mapToDto(Post post){
       PostDto dto = new PostDto();
       dto.setId(post.getId());
       dto.setTitle(post.getTitle());
       dto.setDescription(post.getDescription());
       dto.setContent(post.getContent());
       //it will copy all the data from post to dto

       return dto;
    }


}