package com.techsolutio.solutiostockr.services;

import java.util.List;
import java.util.UUID;

import com.techsolutio.solutiostockr.models.dto.VendorsDto;
import com.techsolutio.solutiostockr.models.entity.Vendors;


public interface VendorServices {
    
    public Vendors createVendors(UUID id, VendorsDto vendorData);

    public List<Vendors> listVendors();

    public Vendors retrieveVendors(UUID id);

    public Vendors updateVendors(UUID id, VendorsDto vendorData);

    public void deleteVendors(UUID id);

}
