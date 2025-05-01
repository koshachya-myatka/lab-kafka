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
            "on p.id = c.post_id group by p.id order by count(c.id) desk limit 5", nativeQuery = true)
    List<Post> findTopPostsByNumberComments();

    @Query(value = "select date(p.date_created) as date, count(p.id) as post_count, count(c.id) as comment_count " +
            "from posts p left join comments c on date(p.date_created) = date(c.date_created) " +
            "group by date(p.date_created) order by date(p.date_created) desc", nativeQuery = true)
    List<ReportDto> findPostAndCommentCountsByDate();
}
