package com.pm.patientservice.service;

import com.pm.patientservice.dto.request.PatientRequestDTO;
import com.pm.patientservice.dto.response.PatientResponseDTO;

import java.util.List;


public interface PatientService {
    List<PatientResponseDTO> getPatients();
    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);
}
