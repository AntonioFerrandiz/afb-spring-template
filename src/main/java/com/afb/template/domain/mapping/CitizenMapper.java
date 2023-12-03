package com.afb.template.domain.mapping;

import com.afb.template.config.mapping.EnhancedModelMapper;
import com.afb.template.domain.dto.Citizen.CitizenResource;
import com.afb.template.domain.dto.Citizen.CreateCitizenResource;
import com.afb.template.domain.dto.Citizen.UpdateCitizenResource;
import com.afb.template.domain.model.Citizen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CitizenMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public CitizenResource toResource(Citizen model){
        return mapper.map(model, CitizenResource.class);
    }

    public Page<CitizenResource> modelListToPage(List<Citizen> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, CitizenResource.class), pageable, modelList.size());
    }

    public List<CitizenResource> modelListToList(List<Citizen> modelList) {
        return mapper.mapList(modelList, CitizenResource.class);
    }

    public Citizen toModel(CreateCitizenResource resource) {
        return mapper.map(resource, Citizen.class);
    }

    public Citizen toModel(UpdateCitizenResource resource) {
        return mapper.map(resource, Citizen.class);
    }
}
