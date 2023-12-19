package com.afb.template.repository;

import com.afb.template.domain.model.Citizen;
import com.afb.template.domain.model.Users;
import com.afb.template.domain.repository.CitizenRepository;
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
public class CitizenRepositoryTests {
    @Autowired
    private CitizenRepository citizenRepository;

    @Test
    public void CitizenRepository_SaveAll_ReturnSavedCitizen(){
        // Arrange
        Citizen citizen = Citizen.builder()
                .alias("afb01")
                .birthdayDate(new Date())
                .build();
        // Act
        Citizen savedCitizen = citizenRepository.save(citizen);
        // Assert
        Assertions.assertThat(savedCitizen).isNotNull();
        Assertions.assertThat(savedCitizen.getId()).isGreaterThan(0);
    }

    @Test
    public void CitizenRepository_GetAll_ReturnMoreThenOneCitizen(){
        // Arrange
        Citizen citizenOne = Citizen.builder()
                .alias("afb01")
                .birthdayDate(new Date())
                .build();
        Citizen citizenTwo = Citizen.builder()
                .alias("afb02")
                .birthdayDate(new Date())
                .build();
        // Act
        this.citizenRepository.save(citizenOne);
        this.citizenRepository.save(citizenTwo);
        List<Citizen> citizenList = this.citizenRepository.findAll();
        // Assert
        Assertions.assertThat(citizenList).isNotNull();
        Assertions.assertThat(citizenList.size()).isEqualTo(2);
    }

    @Test
    public void CitizenRepository_FindById_ReturnCitizenNotNull(){
        // Arrange
        Citizen citizen = Citizen.builder()
                .alias("afb01")
                .birthdayDate(new Date())
                .build();
        // Act
        this.citizenRepository.save(citizen);
        Citizen optionalCitizen = this.citizenRepository.findById(citizen.getId()).get();
        // Assert
        Assertions.assertThat(optionalCitizen).isNotNull();
    }

    @Test
    public void CitizenRepository_FindByAlias_ReturnCitizenNotNull(){
        // Arrange
        Citizen citizen = Citizen.builder()
                .alias("afb01")
                .birthdayDate(new Date())
                .build();
        // Act
        this.citizenRepository.save(citizen);
        Optional<Citizen> optionalCitizen = this.citizenRepository.findCitizenByAlias(citizen.getAlias());
        // Assert
        Assertions.assertThat(optionalCitizen).isNotNull();
        Assertions.assertThat(optionalCitizen).isNotEmpty();
    }

    @Test
    public void CitizenRepository_ExistsByAlias_ReturnCitizenNotNull(){
        // Arrange
        Citizen citizen = Citizen.builder()
                .alias("afb01")
                .birthdayDate(new Date())
                .build();
        // Act
        this.citizenRepository.save(citizen);
        Boolean existsByAlias = this.citizenRepository.existsByAlias(citizen.getAlias());
        // Assert
        Assertions.assertThat(existsByAlias).isNotNull();
        Assertions.assertThat(existsByAlias).isTrue();
    }

    @Test
    public void CitizenRepository_DeleteCitizen_ReturnCitizenEmpty(){
        // Arrange
        Citizen citizen = Citizen.builder()
                .alias("afb01")
                .birthdayDate(new Date())
                .build();
        // Act
        this.citizenRepository.save(citizen);
        this.citizenRepository.deleteById(citizen.getId());
        Optional<Citizen> optionalCitizen = this.citizenRepository.findById(citizen.getId());
        // Assert
        Assertions.assertThat(optionalCitizen).isEmpty();
    }

    @Test
    public void CitizenRepository_UpdateCitizen_ReturnCitizenNotNull(){
        // Arrange
        Citizen citizen = Citizen.builder()
                .alias("afb01")
                .birthdayDate(new Date())
                .build();
        // Act
        this.citizenRepository.save(citizen);
        Citizen citizenSave = this.citizenRepository.findById(citizen.getId()).get();
        citizenSave.setAlias("afb02");
        citizenSave.setBirthdayDate(new Date());
        Citizen citizenUpdated = this.citizenRepository.save(citizenSave);
        // Assert
        Assertions.assertThat(citizenUpdated.getAlias()).isNotNull();
        Assertions.assertThat(citizenUpdated.getBirthdayDate()).isNotNull();
    }
}
