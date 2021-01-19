package com.vg.hm.samples.usermanagementsvc.service;

import com.vg.hm.samples.usermanagementsvc.service.model.Make;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface VehicleService {
    /**
     * Gets all car makes
     * @return list of Make objects
     */
    public List<Make> getMakes() throws IOException;

    /**
     * Gets a list of predefined makes
     * @return
     */
    public List<Make> getDummyMakes();
}
