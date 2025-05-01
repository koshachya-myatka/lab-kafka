package ru.paramonova.dataservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.paramonova.dataservice.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
