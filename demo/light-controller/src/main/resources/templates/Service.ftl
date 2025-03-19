package com.light.service;

import com.light.model.${entityName};
import com.light.mapper.${entityName}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${entityName}Service {

    @Autowired
    private ${entityName}Mapper ${entityNameLowercase}Mapper;

    public List<${entityName}> getAll() {
        return ${entityNameLowercase}Mapper.getAll();
    }

    public ${entityName} getById(Long id) {
        return ${entityNameLowercase}Mapper.getById(id);
    }

    public ${entityName} save(${entityName} ${entityNameLowercase}) {
        ${entityNameLowercase}Mapper.save(${entityNameLowercase});
        return ${entityNameLowercase};
    }

    public ${entityName} update(Long id, ${entityName} ${entityNameLowercase}) {
        ${entityNameLowercase}Mapper.update(id, ${entityNameLowercase});
        return ${entityNameLowercase};
    }

    public void delete(Long id) {
        ${entityNameLowercase}Mapper.delete(id);
    }
}