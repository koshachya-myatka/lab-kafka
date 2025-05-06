package ru.paramonova.dataservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.paramonova.dataservice.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u.* from users u join posts p " +
            "on u.id = p.author_id group by u.id order by count(p.id) desc limit 1", nativeQuery = true)
    Optional<User> findTopUserByNumberPosts();
}
