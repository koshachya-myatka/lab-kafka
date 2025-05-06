package ru.paramonova.dataservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.paramonova.dataservice.dto.ReportDto;
import ru.paramonova.dataservice.models.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "select p.* from posts p join comments c " +
            "on p.id = c.post_id group by p.id order by count(c.id) desc limit 5", nativeQuery = true)
    List<Post> findTopPostsByNumberComments();

    @Query(value = """
            SELECT 
                COALESCE(p.date, c.date) AS date,
                COALESCE(p.post_count, 0) AS post_count,
                COALESCE(c.comment_count, 0) AS comment_count
            FROM 
                (SELECT DATE(date_created) AS date, COUNT(id) AS post_count 
                 FROM posts 
                 GROUP BY DATE(date_created)) p
            FULL JOIN 
                (SELECT DATE(date_created) AS date, COUNT(id) AS comment_count 
                 FROM comments 
                 GROUP BY DATE(date_created)) c 
            ON p.date = c.date
            ORDER BY date DESC
            """, nativeQuery = true)
    List<ReportDto> findPostAndCommentCountsByDate();
}
