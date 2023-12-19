package com.afb.template.repository;

import com.afb.template.domain.model.Admin;
import com.afb.template.domain.repository.AdminRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AdminRepositoryTests {
    @Autowired
    private AdminRepository adminRepository;
    @Test
    public void AdminRepository_SaveAll_ReturnSavedAdmin(){
        // Arrange
        Admin admin = Admin.builder()
                .alias("afb01")
                .build();
        // Act
        Admin savedAdmin = adminRepository.save(admin);
        // Assert
        Assertions.assertThat(savedAdmin).isNotNull();
        Assertions.assertThat(savedAdmin.getId()).isGreaterThan(0);
    }

    @Test
    public void AdminRepository_GetAll_ReturnMoreThenOneAdmin(){
        // Arrange
        Admin adminOne = Admin.builder()
                .alias("afb01")
                .build();
        Admin adminTwo = Admin.builder()
                .alias("afb02")
                .build();
        // Act
        this.adminRepository.save(adminOne);
        this.adminRepository.save(adminTwo);
        List<Admin> adminList = this.adminRepository.findAll();
        // Assert
        Assertions.assertThat(adminList).isNotNull();
        Assertions.assertThat(adminList.size()).isEqualTo(2);
    }

    @Test
    public void AdminRepository_FindById_ReturnAdminNotNull(){
        // Arrange
        Admin admin = Admin.builder()
                .alias("afb01")
                .build();
        // Act
        this.adminRepository.save(admin);
        Admin optionalAdmin = this.adminRepository.findById(admin.getId()).get();
        // Assert
        Assertions.assertThat(optionalAdmin).isNotNull();
    }

    @Test
    public void AdminRepository_FindByAlias_ReturnAdminNotNull(){
        // Arrange
        Admin admin = Admin.builder()
                .alias("afb01")
                .build();
        // Act
        this.adminRepository.save(admin);
        Optional<Admin> optionalAdmin = this.adminRepository.findAdminByAlias(admin.getAlias());
        // Assert
        Assertions.assertThat(optionalAdmin).isNotNull();
        Assertions.assertThat(optionalAdmin).isNotEmpty();
    }

    @Test
    public void AdminRepository_ExistsByAlias_ReturnAdminNotNull(){
        // Arrange
        Admin admin = Admin.builder()
                .alias("afb01")
                .build();
        // Act
        this.adminRepository.save(admin);
        Boolean existsByAlias = this.adminRepository.existsByAlias(admin.getAlias());
        // Assert
        Assertions.assertThat(existsByAlias).isNotNull();
        Assertions.assertThat(existsByAlias).isTrue();
    }

    @Test
    public void AdminRepository_DeleteAdmin_ReturnAdminEmpty(){
        // Arrange
        Admin admin = Admin.builder()
                .alias("afb01")
                .build();
        // Act
        this.adminRepository.save(admin);
        this.adminRepository.deleteById(admin.getId());
        Optional<Admin> optionalAdmin = this.adminRepository.findById(admin.getId());
        // Assert
        Assertions.assertThat(optionalAdmin).isEmpty();
    }

    @Test
    public void AdminRepository_UpdateAdmin_ReturnAdminNotNull(){
        // Arrange
        Admin admin = Admin.builder()
                .alias("afb01")
                .build();
        // Act
        this.adminRepository.save(admin);
        Admin adminSave = this.adminRepository.findById(admin.getId()).get();
        adminSave.setAlias("afb02");
        Admin adminUpdated = this.adminRepository.save(adminSave);
        // Assert
        Assertions.assertThat(adminUpdated.getAlias()).isNotNull();
    }
}
