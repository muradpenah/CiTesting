package com.example.usertesthometask.controller;

import com.example.usertesthometask.dto.MedicineRequestDto;
import com.example.usertesthometask.dto.MedicineResponseDto;
import com.example.usertesthometask.service.MedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/medicines")
public class MedicineController {
    private final MedicineService medicineService;
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PostMapping("/create")
    public ResponseEntity<MedicineResponseDto> post(@RequestBody MedicineRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicineService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(medicineService.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MedicineResponseDto>> getAll() {
        List<MedicineResponseDto> medicines = medicineService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(medicines);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        medicineService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Medicine deleted :"+id);
    }
}
