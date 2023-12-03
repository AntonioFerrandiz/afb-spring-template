package com.afb.template.domain.mapping;

import com.afb.template.config.mapping.EnhancedModelMapper;
import com.afb.template.domain.dto.User.UserResource;
import com.afb.template.domain.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UsersMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public UserResource toResource(Users model){
        return mapper.map(model, UserResource.class);
    }

    public Page<UserResource> modelListToPage(List<Users> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, UserResource.class), pageable, modelList.size());
    }

    public List<UserResource> modelListToList(List<Users> modelList) {
        return mapper.mapList(modelList, UserResource.class);
    }
}
