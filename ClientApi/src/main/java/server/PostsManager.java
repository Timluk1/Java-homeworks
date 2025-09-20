package server;

import java.util.Arrays;

public class PostsManager {
    private static Post[] posts = {
            new Post(1, "Hello World", "This is the first post."),
            new Post(2, "Java Tips", "Some useful Java tips."),
            new Post(3, "Mocking Data", "How to mock data in Java.")
    };

    public static Post[] getPosts() {
        return posts;
    }

    public static Post[] getPostById(int id) {
        return Arrays.stream(posts)
                .filter(post -> post.getId() == id)
                .toArray(Post[]::new);
    }

    public static Post[] deletePostById(int id) {
        posts = Arrays.stream(posts)
                .filter(post -> post.getId() != id)
                .toArray(Post[]::new);
        return posts;
    }

    public static int getNewId() {
        return posts.length + 1;
    }

    public static void addPost(Post post) {
        Post[] newPosts = Arrays.copyOf(posts, posts.length + 1);
        newPosts[newPosts.length - 1] = new Post(getNewId(), post.getTitle(), post.getContent());
        posts = newPosts;
    }

    public static boolean updatePost(Post updatedPost) {
        boolean found = false;
        for (int i = 0; i < posts.length; i++) {
            if (posts[i].getId() == updatedPost.getId()) {
                posts[i] = new Post(updatedPost.getId(), updatedPost.getTitle(), updatedPost.getContent());
                found = true;
                break;
            }
        }
        return found;
    }
}
