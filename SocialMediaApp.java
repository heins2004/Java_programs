import java.util.Scanner;

interface Shareable {
    void sharePost();
}

class SocialMediaUser {
    String name;

    SocialMediaUser(String name) {
        this.name = name;
    }

    void moderatePost() {
        System.out.println("General post moderation");
    }
}

class Admin extends SocialMediaUser implements Shareable {

    Admin(String name) {
        super(name);
    }

    @Override
    void moderatePost() {
        System.out.println("Admin moderates posts strictly");
    }

    public void sharePost() {
        System.out.println("Admin shared a post");
    }
}

class Member extends SocialMediaUser implements Shareable {

    Member(String name) {
        super(name);
    }
    @Override
    void moderatePost() {
        System.out.println("Member reports posts");
    }

    public void sharePost() {
        System.out.println("Member shared a post");
    }
}

class PostThread extends Thread {
    SocialMediaUser user;
    String postTitle;

    PostThread(SocialMediaUser user, String postTitle) {
        this.user = user;
        this.postTitle = postTitle;
    }

    public void run() {
        System.out.println(user.name + " posted: " + postTitle);
        user.moderatePost();
        System.out.println("----------------------");
    }
}
public class SocialMediaApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of posts: ");
        int n = sc.nextInt();
        sc.nextLine();
        String[] posts = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter post title: ");
            posts[i] = sc.nextLine();
        }

        System.out.print("Enter post to search: ");
        String search = sc.nextLine();
        boolean found = false;
        for (String p : posts) {
            if (p.equalsIgnoreCase(search)) {
                found = true;
                break;
            }
        }
        if (found)
            System.out.println("Post found!");
        else
            System.out.println("Post not found!");

        Member member = new Member("MemberUser");

        System.out.println("----- Posting (Threads) -----");
        for (String p : posts) {
            PostThread t = new PostThread(member, p);
            t.start();
        }

        sc.close();
    }
}