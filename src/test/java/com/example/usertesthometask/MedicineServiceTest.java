package com.example.usertesthometask;

import com.example.usertesthometask.dto.MedicineRequestDto;
import com.example.usertesthometask.dto.MedicineResponseDto;
import com.example.usertesthometask.mapper.MedicineMapper;
import com.example.usertesthometask.model.MedicineEntity;
import com.example.usertesthometask.repo.MedicineRepository;
import com.example.usertesthometask.service.MedicineService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class MedicineServiceTest {

    @Mock
    private MedicineRepository medicineRepository;

    @Mock
    private MedicineMapper medicineMapper;

    @InjectMocks
    private MedicineService medicineService;


    @Test
    void createMedicineTest(){

        MedicineRequestDto medicineRequestDto = new MedicineRequestDto("Nimesil",
                "Berlin Chemie" ,"Qizdirma eleyhine derman");

        MedicineEntity medicineEntity = new MedicineEntity(1L,"Nimesil",
                "Berlin Chemie" ,"Qizdirma eleyhine derman");

        MedicineResponseDto medicineResponseDto = new MedicineResponseDto(1L,"Nimesil",
                "Berlin Chemie" ,"Qizdirma eleyhine derman");

        when(medicineMapper.toEntity(medicineRequestDto)).thenReturn(medicineEntity);
        when(medicineRepository.save(medicineEntity)).thenReturn(medicineEntity);
        when(medicineMapper.toDto(medicineEntity)).thenReturn(medicineResponseDto);

        MedicineResponseDto result = medicineService.create(medicineRequestDto);

        assertNotNull(result);
        assertEquals(result.getId(),1L);
        assertEquals(result.getName(),"Nimesil");
        assertEquals(result.getBrend(),"Berlin Chemie");
        assertEquals(result.getDescription(),"Qizdirma eleyhine derman");

        verify(medicineMapper).toEntity(medicineRequestDto);
        verify(medicineRepository).save(medicineEntity);
        verify(medicineMapper).toDto(medicineEntity);
    }

    @Test
    void getMedicineByIdTest(){

        Long id = 1L;

        MedicineEntity medicineEntity = new MedicineEntity(id,"Nimesil",
                "Berlin Chemie" ,"Qizdirma eleyhine derman");

        MedicineResponseDto medicineResponseDto = new MedicineResponseDto(id,"Nimesil",
                "Berlin Chemie" ,"Qizdirma eleyhine derman");

        when(medicineRepository.findById(id)).thenReturn(Optional.of(medicineEntity));
        when(medicineMapper.toDto(medicineEntity)).thenReturn(medicineResponseDto);

        MedicineResponseDto result = medicineService.getById(id);

        assertNotNull(result);
        assertEquals(result.getId(),id);
        assertEquals(result.getName(),"Nimesil");
        assertEquals(result.getBrend(),"Berlin Chemie");
        assertEquals(result.getDescription(),"Qizdirma eleyhine derman");

        verify(medicineRepository).findById(id);
        verify(medicineMapper).toDto(medicineEntity);
    }

    @Test
    void getAllMedicinesTest(){

        MedicineEntity medicineEntity1 = new MedicineEntity(1L,"Nimesil",
                "Berlin Chemie" ,"Qizdirma eleyhine derman");
        MedicineEntity medicineEntity2 = new MedicineEntity(2L,"Paraferin",
                "Bilim", "Soyuqdeyme ve Qripe qarsi");

        MedicineResponseDto medicineResponseDto1 = new MedicineResponseDto(1L,"Nimesil",
                "Berlin Chemie" ,"Qizdirma eleyhine derman");
        MedicineResponseDto medicineResponseDto2 = new MedicineResponseDto(2L,"Paraferin",
                "Bilim", "Soyuqdeyme ve Qripe qarsi");

        when(medicineRepository.findAll()).thenReturn(List.of(medicineEntity1, medicineEntity2));
        when(medicineMapper.toDto(medicineEntity1)).thenReturn(medicineResponseDto1);
        when(medicineMapper.toDto(medicineEntity2)).thenReturn(medicineResponseDto2);

        List<MedicineResponseDto> result = medicineService.getAll();

        assertNotNull(result);
        assertEquals(result.size(),2);

        assertEquals(result.get(0).getName(),"Nimesil");
        assertEquals(result.get(0).getBrend(),"Berlin Chemie");
        assertEquals(result.get(0).getDescription(),"Qizdirma eleyhine derman");

        assertEquals(result.get(1).getName(),"Paraferin");
        assertEquals(result.get(1).getBrend(),"Bilim");
        assertEquals(result.get(1).getDescription(),"Soyuqdeyme ve Qripe qarsi");

        verify(medicineRepository).findAll();
        verify(medicineMapper).toDto(medicineEntity1);
        verify(medicineMapper).toDto(medicineEntity2);
    }

    @Test
    void updateMedicineTest(){
        Long id = 1L;

        MedicineRequestDto medicineRequestDto = new MedicineRequestDto("Paraferin",
                "Bilim", "Soyuqdeyme ve Qripe qarsi");

        MedicineEntity medicineEntity = new MedicineEntity(id,"Nimesil",
                "Berlin Chemie", "Qizdirma eleyhine derman");

        MedicineResponseDto medicineResponseDto = new MedicineResponseDto(id,"Paraferin",
                "Bilim", "Soyuqdeyme ve Qripe qarsi");

        when(medicineRepository.findById(id)).thenReturn(Optional.of(medicineEntity));
        doNothing().when(medicineMapper).update(medicineEntity, medicineRequestDto);
        when(medicineRepository.save(medicineEntity)).thenReturn(medicineEntity);
        when(medicineMapper.toDto(medicineEntity)).thenReturn(medicineResponseDto);

        MedicineResponseDto result = medicineService.update(id, medicineRequestDto);

        assertNotNull(result);
        assertEquals(result.getId(),id);
        assertEquals(result.getName(),"Paraferin");
        assertEquals(result.getBrend(),"Bilim");
        assertEquals(result.getDescription(),"Soyuqdeyme ve Qripe qarsi");

        verify(medicineRepository).findById(id);
        verify(medicineMapper).update(medicineEntity, medicineRequestDto);
        verify(medicineRepository).save(medicineEntity);
        verify(medicineMapper).toDto(medicineEntity);
    }
}
