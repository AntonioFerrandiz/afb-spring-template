package com.afb.template.service;

import com.afb.template.config.mapping.EnhancedModelMapper;
import com.afb.template.domain.dto.Citizen.CitizenResource;
import com.afb.template.domain.dto.Jwt.ChangePasswordResource;
import com.afb.template.domain.model.Citizen;
import com.afb.template.domain.repository.CitizenRepository;
import com.afb.template.domain.repository.UsersRepository;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTests {
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private CitizenRepository citizenRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RequestUtil requestUtil;
    @Mock
    private EnhancedModelMapper mapper;
    @Mock
    private AuthServiceImpl authService;
    @InjectMocks
    private CitizenServiceImpl citizenService;

    @Test
    public void AuthService_EncodePassword_ReturnPasswordEncoded(){
        // Arrange
        String password = "Test123!";

        // Act
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn(Mockito.anyString());
        String passwordEncoded = this.passwordEncoder.encode(password);

        // Assert
        Assertions.assertThat(passwordEncoded).isNotEqualTo(password);
        Assertions.assertThat(passwordEncoded).isNotNull();
    }

    @Test
    public void AuthService_UpdatePassword_PasswordChangeMessage() throws Exception {
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
        String oldPassword = citizen.getPassword();
        ChangePasswordResource changePasswordResource = ChangePasswordResource.builder()
                .email("antoniojfb01@gmail.com")
                .newPassword("123456")
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

        Mockito.when(this.authService.registerCitizen(citizen)).thenReturn(citizen);
        Mockito.when(mapper.map(Mockito.any(), Mockito.eq(CitizenResource.class))).thenReturn(citizenResource);
        Citizen citizenAux = this.citizenService.save(citizen);
        CitizenResource citizenResourceAux = mapper.map(citizenAux, CitizenResource.class);

        Mockito.when(this.authService.updatePassword(changePasswordResource)).thenReturn(Mockito.any());
        String changePassword = this.authService.updatePassword(changePasswordResource);
        Assertions.assertThat(citizen.getPassword()).isNotEqualTo(oldPassword);

    }
}
