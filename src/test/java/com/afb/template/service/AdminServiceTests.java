package com.afb.template.service;

import com.afb.template.config.mapping.EnhancedModelMapper;
import com.afb.template.domain.dto.Admin.AdminResource;
import com.afb.template.domain.model.Admin;
import com.afb.template.domain.repository.AdminRepository;
import com.afb.template.service.impl.AdminServiceImpl;
import com.afb.template.service.impl.AuthServiceImpl;
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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTests {
    @Mock
    private EnhancedModelMapper mapper;
    @Mock
    private AuthServiceImpl authService;
    @Mock
    private RequestUtil requestUtil;
    @Mock
    private AdminRepository adminRepository;
    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    public void AdminService_SaveAdmin_ReturnAdminDto() throws Exception {
        Admin admin = Admin.builder().alias("afb01").firstname("Antonio").lastname("Ferrandiz").fullname("Antonio Ferrandiz").email("antoniojfb01@gmail.com").password("test123").active(true).registrationDate(new Date()).build();

        AdminResource adminResource = AdminResource.builder().alias("afb01").firstname("Antonio").lastname("Ferrandiz").active(true).registrationDate(new Date()).build();

        Mockito.when(this.requestUtil.isAdmin()).thenReturn(true);
        Mockito.when(this.authService.registerAdmin(admin)).thenReturn(admin);
        Mockito.when(mapper.map(Mockito.any(), Mockito.eq(AdminResource.class))).thenReturn(adminResource);
        Admin adminAux = this.adminService.save(admin);
        AdminResource adminResourceAux = mapper.map(adminAux, AdminResource.class);

        Assertions.assertThat(adminResourceAux).isNotNull();
    }

    @Test
    public void AdminService_SaveAdmin_existByAlias_ReturnRuntimeException() {
        Admin admin = Admin.builder().alias("afb01").firstname("Antonio").lastname("Ferrandiz").fullname("Antonio Ferrandiz").email("antoniojfb01@gmail.com").password("test123").active(true).registrationDate(new Date()).build();


        Mockito.when(this.requestUtil.isAdmin()).thenReturn(true);
        Mockito.when(this.adminRepository.existsByAlias(admin.getAlias())).thenReturn(true);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> this.adminService.save(admin));

        String expectedErrorMessage = "Ya existe un admin registrado con el alias afb01";
        Assertions.assertThat(expectedErrorMessage).isEqualTo(exception.getMessage());
    }

    @Test
    public void AdminService_SaveAdmin_existByEmail_ReturnRuntimeException() {
        Admin admin = Admin.builder().alias("afb01").firstname("Antonio").lastname("Ferrandiz").fullname("Antonio Ferrandiz").email("antoniojfb01@gmail.com").password("test123").active(true).registrationDate(new Date()).build();

        Mockito.when(this.requestUtil.isAdmin()).thenReturn(true);
        Mockito.when(this.adminRepository.existsByEmail(admin.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> this.adminService.save(admin));

        String expectedErrorMessage = "Ya existe un admin registrado con el email antoniojfb01@gmail.com";
        Assertions.assertThat(expectedErrorMessage).isEqualTo(exception.getMessage());
    }
}
