package com.afb.template.config.mapping;

import com.afb.template.domain.mapping.AdminMapper;
import com.afb.template.domain.mapping.CitizenMapper;
import com.afb.template.domain.mapping.UsersMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("enhancedModelMapperConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper() {
        return new EnhancedModelMapper();
    }
    @Bean
    public UsersMapper usersMapper(){
        return new UsersMapper();
    }
    @Bean
    public AdminMapper adminMapper(){
        return new AdminMapper();
    }
    @Bean
    public CitizenMapper citizenMapper() {
        return new CitizenMapper();
    }

}
