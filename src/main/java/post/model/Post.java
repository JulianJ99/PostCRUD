package post.model;


import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.persistence.*;
@Entity
@Table(name = "posts")
public class Post {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "post")
    private String post;

    @Column(name = "song")
    private String song;

    @Column(name = "username")
    private String username;

    @Column(name = "userimage")
    private String userimage;

    public Post() {
    }

    public Post(String post, String song, String username, String userimage) {
        this.post = post;
        this.song = song;
        this.username = username;
        this.userimage = userimage;
    }

    public long getId() {
        return id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public void setId(Long id) {
        this.id = id;
    }


}