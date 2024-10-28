package main;

public class Main2 {
    public static void main(String[] args) {
        Post post = new NewPost();
//        PrintPost printPost = new PrintLnPost(post);
//        printPost.print();  //This is all post data sample
//        printPost.print();  //This is all post data sample

        PrintPost printPost = new PrintNoLnPost(post);
        printPost.print();
        printPost.print();  //This is all post data sampleThis is all post data sample

    }
}

interface Post {
    String getAllPosts();
}

class NewPost implements Post {
    private String postData = "This is all post data sample";
    @Override
    public String getAllPosts() {
        return postData;
    }
}

interface PrintPost {
    void print();
}

class PrintLnPost implements PrintPost {
    private final Post post;

    public PrintLnPost(Post post) {     //생성자 주입 권장
        this.post = post;
    }

    @Override
    public void print() {
        System.out.println(post.getAllPosts());
    }
}

class PrintNoLnPost implements PrintPost {
    private Post post;

    public PrintNoLnPost(Post post) {
        this.post = post;
    }

    @Override
    public void print() {
        System.out.print(post.getAllPosts());
    }
}

