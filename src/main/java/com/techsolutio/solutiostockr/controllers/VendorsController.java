package com.techsolutio.solutiostockr.controllers;

import java.util.UUID;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techsolutio.solutiostockr.models.dto.VendorsDto;
import com.techsolutio.solutiostockr.models.entity.Vendors;
import com.techsolutio.solutiostockr.services.VendorServices;

@RestController
@RequestMapping("/api/vendors")
public class VendorsController {
    
    private VendorServices vendorServices;

    public VendorsController(VendorServices vendorServices) {
        this.vendorServices = vendorServices;
    }

    @PostMapping
    public ResponseEntity<Vendors> createVendors(@RequestBody VendorsDto vendorsData){
        final UUID id = new UUID(1, 999);
        final Vendors newVendor = vendorServices.createVendors(id, vendorsData);

        return new ResponseEntity<>(newVendor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Vendors>> listVendors(){
        final List<Vendors> vendorsList = vendorServices.listVendors();

        return new ResponseEntity<>(vendorsList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendors> retrieveVendors(@PathVariable UUID id){
        final Vendors foundVendor = vendorServices.retrieveVendors(id);

        return new ResponseEntity<>(foundVendor, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vendors> updateVendors(@PathVariable UUID id, @RequestBody VendorsDto vendorsData){
        final Vendors updatedVendor = vendorServices.updateVendors(id, vendorsData);

        return new ResponseEntity<>(updatedVendor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendors(@PathVariable UUID id){
        vendorServices.deleteVendors(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
