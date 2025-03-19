package com.light.mapper;

import com.light.model.${entityName};
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ${entityName}Mapper {

    List<${entityName}> getAll();

    ${entityName} getById(Long id);

    void save(${entityName} ${entityNameLowercase});

    void update(Long id, ${entityName} ${entityNameLowercase});

    void delete(Long id);
}