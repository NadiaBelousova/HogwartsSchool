package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Avatar;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository <Avatar,Long> {
    Avatar findByStudentId(long id);
    @Query (value = "select *from avatar a",nativeQuery = true)
    Collection<Avatar> getAll (Integer pageNumber, Integer pageSize);
}
