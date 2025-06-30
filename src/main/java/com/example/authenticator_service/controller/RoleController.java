package com.example.authenticator_service.controller;

import com.example.authenticator_service.dtos.RoleDTO;
import com.example.authenticator_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<String> createRole(@RequestBody RoleDTO roleDTO) {
        return ResponseEntity.ok(roleService.createRole(roleDTO));
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }


    @GetMapping("/hello")
    public void hello() {
        int[] arr=new int[100];
        System.out.println("start time: "+System.currentTimeMillis());
        for(int i=0;i<arr.length;i++){
            System.out.print("PROCESSING..."+arr[i]);
        }
        System.out.println("end time: "+System.currentTimeMillis());

        System.out.println("start time foreach : "+System.currentTimeMillis());
        for(int a:arr){
            System.out.print("PROCESSING..."+a);
        }
        System.out.println("end time: "+System.currentTimeMillis());

    }
}
