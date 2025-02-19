package com.ms.vidhyalebox.role;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.sharedapi.generic.APiResponse;


@RestController
@RequestMapping("/role")
public class RoleController {

private final RoleRepo roleRepo;

    public RoleController(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @PostMapping("/")
    public ResponseEntity<APiResponse<RoleEntity>> addRole(@RequestBody RoleEntity roleEntity) {
        Optional<RoleEntity> role = roleRepo.findByName(roleEntity.getName());
        if(role.isPresent()){
          return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APiResponse<>("error",
                    "Role already exist",
                      null,
                    null));
        }
        RoleEntity save = roleRepo.save(roleEntity);
        return ResponseEntity.ok(new APiResponse<>("success",
                                        "Role created successfully",
                                                save,
                                                null));
    }

    @GetMapping("/all")
    public List<RoleEntity> getAll() {
        List<RoleEntity> save = roleRepo.findAll();
        return save;
    }
}
