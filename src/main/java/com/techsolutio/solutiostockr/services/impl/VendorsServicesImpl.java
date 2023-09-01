package com.techsolutio.solutiostockr.services.impl;

import java.util.UUID;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.techsolutio.solutiostockr.dto.VendorsDto;
import com.techsolutio.solutiostockr.exceptions.AppException;
import com.techsolutio.solutiostockr.models.Users;
import com.techsolutio.solutiostockr.models.Vendors;
import com.techsolutio.solutiostockr.repositories.UserRepository;
import com.techsolutio.solutiostockr.repositories.VendorRepository;
import com.techsolutio.solutiostockr.services.VendorServices;

public class VendorsServicesImpl implements VendorServices {
    
    private VendorRepository vendorRepository;
    private UserRepository usersRepository;

    public VendorsServicesImpl(VendorRepository vendorRepository, UserRepository usersRepository) {
        this.vendorRepository = vendorRepository;
        this.usersRepository = usersRepository;
    }

    public Vendors createVendors(UUID id, VendorsDto vendorsData){

        final Users foundUser = usersRepository.findById(id).orElse(null);

        if(foundUser == null) throw new AppException("Invalid token", HttpStatus.FORBIDDEN);

        final Vendors newVendors = new Vendors(
            vendorsData.getVendorName(),
            vendorsData.getRegistration(),
            foundUser
        );

        return vendorRepository.save(newVendors);
    }

    public List<Vendors> listVendors(){
        return vendorRepository.findAll();
    }

    public Vendors retrieveVendors(UUID id){
        final Vendors foundVendor = vendorRepository.findById(id).orElse(null);

        if(foundVendor == null) throw new AppException("Vendor not found", HttpStatus.NOT_FOUND);

        return foundVendor;
    }

    public Vendors updateVendors(UUID id, VendorsDto vendorsData){
        final Vendors foundVendor = vendorRepository.findById(id).orElse(null);

        if(foundVendor == null) throw new AppException("Vendor not found", HttpStatus.NOT_FOUND);

        foundVendor.setVendorName(vendorsData.getVendorName());
        foundVendor.setRegistration(vendorsData.getRegistration());

        return vendorRepository.save(foundVendor);
    }

    public void deleteVendors(UUID id){
        final Vendors foundVendor = vendorRepository.findById(id).orElse(null);

        if(foundVendor == null) throw new AppException("Vendor not found", HttpStatus.NOT_FOUND);

        vendorRepository.delete(foundVendor);
    }
}
