package ru.skypro.homework.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.users.Register;

public interface RegisterRepository extends JpaRepository<Register, Integer> {
}
