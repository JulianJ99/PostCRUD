package post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import post.model.Post;
import post.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void createPostTest() throws Exception{

        // given - precondition or setup
        Post post = new Post("testpost", "testsong", "testuser", "testimage");


        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(post)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.post",
                        is(post.getPost())))
                .andExpect(jsonPath("$.song",
                        is(post.getSong())))
                .andExpect(jsonPath("$.username",
                        is(post.getUsername())))
                .andExpect(jsonPath("$.userimage",
                        is(post.getUserimage())));

    }


    @Test
    public void getAllPostsTest() throws Exception{
        // given - precondition or setup
        List<Post> listOfPosts = new ArrayList<>();
        listOfPosts.add(new Post("testpost1", "testsong1", "testuser1", "testimage1"));
        listOfPosts.add(new Post("testpost2", "testsong2", "testuser2", "testimage2"));
        postRepository.saveAll(listOfPosts);
        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/posts"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfPosts.size())));

    }

}