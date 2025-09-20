package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/post")
public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        Post[] posts = PostsManager.getPosts();
        String json = JsonParser.createJson(posts);

        PrintWriter out = resp.getWriter();
        out.println(json);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        StringBuilder body = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            Post newPost = mapper.readValue(body.toString(), Post.class);

            PostsManager.addPost(newPost);

            PrintWriter out = resp.getWriter();
            out.println(JsonParser.createJson(PostsManager.getPosts()));
            out.close();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = resp.getWriter();
            out.println("{\"error\":\"Invalid JSON\"}");
            out.close();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        StringBuilder body = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            Post updatedPost = mapper.readValue(body.toString(), Post.class);

            boolean success = PostsManager.updatePost(updatedPost);

            PrintWriter out = resp.getWriter();
            if (success) {
                out.println(JsonParser.createJson(PostsManager.getPosts()));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("{\"error\":\"Post not found\"}");
            }
            out.close();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = resp.getWriter();
            out.println("{\"error\":\"Invalid JSON\"}");
            out.close();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = resp.getWriter();
            out.println("{\"error\":\"Missing id parameter\"}");
            out.close();
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            Post[] remainingPosts = PostsManager.deletePostById(id);

            PrintWriter out = resp.getWriter();
            out.println(JsonParser.createJson(remainingPosts));
            out.close();
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = resp.getWriter();
            out.println("{\"error\":\"Invalid id parameter\"}");
            out.close();
        }
    }
}
