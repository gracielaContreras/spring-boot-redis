package com.gcontreras.springbootredis.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcontreras.springbootredis.entities.Role;
import com.gcontreras.springbootredis.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;
    @Autowired
    private ObjectMapper mapper;

    public List<Role> getRoles(){
        return  repository.findAll();
    }

    public Role create(Role role){
        Role rolCreated = repository.save(role);
        return rolCreated;
    }

    public Role update(Integer id, Role role) {
        Optional<Role> result = repository.findById(id);
        if(result.isPresent()){
            return repository.save(role);
        } else {
            throw new ResponseStatusException
                    (HttpStatus.NOT_FOUND, String.format("Role %s not found", id));
        }
    }

    public void delete(Integer id) {
        Optional<Role> result = repository.findById(id);
        if (result.isPresent()){
            repository.delete(result.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role %s not found", id));
        }
    }
}
