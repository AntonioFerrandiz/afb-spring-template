package com.afb.template.service;

import com.afb.template.config.exception.ResourceNotFoundException;
import com.afb.template.config.mapping.EnhancedModelMapper;
import com.afb.template.domain.dto.Citizen.CitizenResource;
import com.afb.template.domain.model.Citizen;
import com.afb.template.domain.repository.CitizenRepository;
import com.afb.template.service.impl.AuthServiceImpl;
import com.afb.template.service.impl.CitizenServiceImpl;
import com.afb.template.util.RequestUtil;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class CitizenServiceTests {
    @Mock
    private EnhancedModelMapper mapper;
    @Mock
    private CitizenRepository citizenRepository;
    @InjectMocks
    private CitizenServiceImpl citizenService;
    @Mock
    private AuthServiceImpl authService;
    @Mock
    private RequestUtil requestUtil;

    @Test
    public void CitizenService_SaveCitizen_ReturnCitizenDto() throws Exception {
        // Arrange
        Citizen citizen = Citizen.builder()
                .alias("afb01")
                .birthdayDate(new Date())
                .firstname("Antonio")
                .lastname("Ferrandiz")
                .fullname("Antonio Ferrandiz")
                .email("antoniojfb01@gmail.com")
                .password("test123")
                .active(true)
                .registrationDate(new Date())
                .build();

        CitizenResource citizenResource = CitizenResource.builder()
                .id(null)
                .alias("afb01")
                .birthdayDate(new Date())
                .firstname("Antonio")
                .lastname("Ferrandiz")
                .active(true)
                .registrationDate(new Date())
                .build();

        // Act
        Mockito.when(this.authService.registerCitizen(citizen)).thenReturn(citizen);
        Mockito.when(mapper.map(Mockito.any(), Mockito.eq(CitizenResource.class))).thenReturn(citizenResource);
        Citizen citizenAux = this.citizenService.save(citizen);
        CitizenResource citizenResourceAux = mapper.map(citizenAux, CitizenResource.class);

        // Assert
        Assertions.assertThat(citizenResourceAux).isNotNull();
    }

    @Test
    public void CitizenService_GetAllCitizen_ReturnCitizenListDto(){
        List<Citizen> citizens = new ArrayList<>();
        List<CitizenResource> citizenResourceArrayList = new ArrayList<>();
        Citizen citizenOne = Citizen.builder()
                .alias("afb01")
                .birthdayDate(new Date())
                .firstname("Antonio")
                .lastname("Ferrandiz")
                .fullname("Antonio Ferrandiz")
                .email("antoniojfb01@gmail.com")
                .password("test123")
                .active(true)
                .registrationDate(new Date())
                .build();
        citizens.add(citizenOne);

        CitizenResource citizenTwo = CitizenResource.builder()
                .alias("afb01")
                .birthdayDate(new Date())
                .firstname("Antonio")
                .lastname("Ferrandiz")
                .active(true)
                .registrationDate(new Date())
                .build();
        citizenResourceArrayList.add(citizenTwo);

        Mockito.when(this.requestUtil.isAdmin()).thenReturn(true);
        Mockito.when(this.citizenRepository.findAll()).thenReturn(citizens);
        Mockito.when(mapper.mapList(Mockito.anyList(), Mockito.eq(CitizenResource.class))).thenReturn(citizenResourceArrayList);

        List<Citizen> list = citizenService.getAllCitizen();
        List<CitizenResource> citizenResourceList =  mapper.mapList(list, CitizenResource.class);

        Assertions.assertThat(citizenResourceList).isNotEmpty();
    }

    @Test
    public void CitizenService_GetAllCitizen_ReturnCitizenEmptyListDto(){
        List<Citizen> citizens = new ArrayList<>();

        Mockito.when(this.requestUtil.isAdmin()).thenReturn(true);
        Mockito.when(this.citizenRepository.findAll()).thenReturn(citizens);

        // Act y Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> citizenService.getAllCitizen());

        String expectedErrorMessage = "No se encontró ningun citizen";
        Assertions.assertThat(expectedErrorMessage).isEqualTo(exception.getMessage());
    }

    @Test
    public void CitizenService_GetAllCitizen_ReturnForbiddenAccessException(){

        Mockito.when(this.requestUtil.isAdmin()).thenReturn(false);

        // Act y Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> citizenService.getAllCitizen());

        String expectedErrorMessage = "No cuenta con los permisos necesarios para realizar esta acción";
        Assertions.assertThat(expectedErrorMessage).isEqualTo(exception.getMessage());
    }

    @Test
    @Transactional
    public void CitizenService_GetCitizenById_ReturnCitizenDto() throws Exception {
        // Arrange
        Citizen citizen = Citizen.builder()
                .alias("afb01")
                .birthdayDate(new Date())
                .firstname("Antonio")
                .lastname("Ferrandiz")
                .fullname("Antonio Ferrandiz")
                .email("antoniojfb01@gmail.com")
                .password("test123")
                .active(true)
                .registrationDate(new Date())
                .build();


        // Act
        Mockito.when(this.authService.registerCitizen(citizen)).thenReturn(citizen);
        Citizen citizenAux = this.citizenService.save(citizen);

        RuntimeException exception = assertThrows(ResourceNotFoundException.class, () -> this.citizenService.getCitizenById(citizenAux.getId()));

        String expectedErrorMessage = "Citizen con id null no existe.";
        Assertions.assertThat(expectedErrorMessage).isEqualTo(exception.getMessage());
    }

}
