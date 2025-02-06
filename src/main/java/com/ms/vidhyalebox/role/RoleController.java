package com.ms.vidhyalebox.role;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/role")
public class RoleController {

private final RoleRepo roleRepo;

    public RoleController(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @PostMapping("/")
    public ResponseEntity<RoleEntity> addRole(@RequestBody RoleEntity roleEntity) {
        RoleEntity save = roleRepo.save(roleEntity);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/all")
    public List<RoleEntity> getAll() {
        List<RoleEntity> save = roleRepo.findAll();
        return save;
    }
}
