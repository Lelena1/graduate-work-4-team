package ru.skypro.homework.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.users.UserCover;

public interface CoverRepository extends JpaRepository<UserCover, Integer> {
    UserCover findByUserId(Integer id);

}
