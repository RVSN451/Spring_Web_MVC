package org.example.repository;

import org.springframework.stereotype.Repository;
import org.example.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepositoryStubImpl implements PostRepository {

    private static AtomicLong postId = new AtomicLong();

    private Map<Long, Post> posts;


    public PostRepositoryStubImpl() {
        posts = new ConcurrentHashMap<>();
    }


    public Map<Long, Post> all() {
        Map<Long, Post> allPosts = new ConcurrentHashMap<>();
        allPosts.putAll(posts);
        return allPosts;
    }

    public Optional<Post> getById(long id) {
        return Optional.of(posts.get(id));
    }

    public Optional<Post> save(Post post) {

        Optional optionalPost;
        if (post.getId() == 0) {
            Post savePost = new Post(postId.incrementAndGet(), post.getContent());
            posts.put(savePost.getId(), savePost);
            optionalPost = Optional.of(savePost);
        } else optionalPost = null;
        return optionalPost;
    }

    public Optional<Post> replace(Post post) {

        Optional optionalPost;
        if (post.getId() > 0) {
            Post savePost = post;
            posts.replace(savePost.getId(), savePost);
            optionalPost = Optional.of(savePost);
        } else optionalPost = null;
        return optionalPost;
    }

    public void removeById(long id) {
        posts.remove(id);
    }
}