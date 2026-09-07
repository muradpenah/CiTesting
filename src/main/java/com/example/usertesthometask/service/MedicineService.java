package com.example.usertesthometask.service;

import com.example.usertesthometask.dto.MedicineRequestDto;
import com.example.usertesthometask.dto.MedicineResponseDto;
import com.example.usertesthometask.mapper.MedicineMapper;
import com.example.usertesthometask.model.MedicineEntity;
import com.example.usertesthometask.repo.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {
    private final MedicineRepository medicineRepository;
    private final MedicineMapper medicineMapper;
    public MedicineService(MedicineRepository medicineRepository, MedicineMapper medicineMapper) {
        this.medicineRepository = medicineRepository;
        this.medicineMapper = medicineMapper;
    }

    public MedicineResponseDto create(MedicineRequestDto dto) {
        MedicineEntity entity = medicineMapper.toEntity(dto);
        MedicineEntity savedEntity = medicineRepository.save(entity);
        return medicineMapper.toDto(savedEntity);
    }

    public MedicineResponseDto getById(Long id) {
        MedicineEntity entity = medicineRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Medicine not found")
        );
        return medicineMapper.toDto(entity);
    }

    public MedicineResponseDto update(Long id, MedicineRequestDto dto) {
        MedicineEntity entity = medicineRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Medicine not found")
        );
        medicineMapper.update(entity, dto);
        MedicineEntity savedEntity = medicineRepository.save(entity);
        return medicineMapper.toDto(savedEntity);
    }

    public List<MedicineResponseDto> getAll() {
        return medicineRepository.findAll().stream().map(medicineMapper::toDto).toList();
    }

    public void delete(Long id) {
        if(!medicineRepository.existsById(id)) {
            throw new RuntimeException("Medicine not found");
        }
        medicineRepository.deleteById(id);
    }
}
