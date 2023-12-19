package com.afb.template.repository;

import com.afb.template.domain.model.Users;
import com.afb.template.domain.repository.CitizenRepository;
import com.afb.template.domain.repository.UsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UsersRepositoryTests {
    @Autowired
    private UsersRepository usersRepository;
    @Test
    public void UsersRepository_ExistsByEmail_ReturnUserNotNull(){
        // Arrange
        Users user = Users.builder()
                .firstname("Antonio")
                .lastname("Ferrandiz")
                .fullname("Antonio Ferrandiz")
                .password("prueba123")
                .email("antoniojfb01@gmail.com")
                .active(Boolean.TRUE)
                .registrationDate(new Date())
                .build();

        // Act
        this.usersRepository.save(user);
        Integer userId = this.usersRepository.getUserIdByEmail("antoniojfb01@gmail.com");
        // Assert
        Assertions.assertThat(userId).isEqualTo(user.getId());
        Assertions.assertThat(userId).isGreaterThan(0);
    }

}
