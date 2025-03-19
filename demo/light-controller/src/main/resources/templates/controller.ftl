package com.light.controller;

import com.light.service.${entityName}Service;
import com.light.model.${entityName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/${entityNameLowercase}")
public class ${entityName}Controller {

    @Autowired
    private ${entityName}Service ${entityNameLowercase}Service;

    @GetMapping
    public List<${entityName}> getAll() {
        return ${entityNameLowercase}Service.getAll();
    }

    @GetMapping("/{id}")
    public ${entityName} getById(@PathVariable Long id) {
        return ${entityNameLowercase}Service.getById(id);
    }

    @PostMapping
    public ${entityName} save(@RequestBody ${entityName} ${entityNameLowercase}) {
        return ${entityNameLowercase}Service.save(${entityNameLowercase});
    }

    @PutMapping("/{id}")
    public ${entityName} update(@PathVariable Long id, @RequestBody ${entityName} ${entityNameLowercase}) {
        return ${entityNameLowercase}Service.update(id, ${entityNameLowercase});
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ${entityNameLowercase}Service.delete(id);
    }
}