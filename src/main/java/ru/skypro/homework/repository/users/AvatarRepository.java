package ru.skypro.homework.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.users.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar,Integer> {
    Optional<Avatar> findByUserId(Integer id);

}
