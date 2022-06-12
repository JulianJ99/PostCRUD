package post.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import post.model.Post;


public interface PostRepository extends JpaRepository<Post, Long> {

}
