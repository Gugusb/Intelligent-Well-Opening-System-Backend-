package com.gugusb.hwics.controller;

import com.gugusb.hwics.mapper.*;
import com.gugusb.hwics.pojo.*;
import com.gugusb.hwics.pojo.dto.PrinterDTO;
import com.gugusb.hwics.pojo.dto.UserDTO;
import com.gugusb.hwics.service.DFP1Service;
import com.gugusb.hwics.utils.MessageRespnser;
import com.gugusb.hwics.utils.SimpleExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/printer")
public class DataPrinter {
    @Autowired
    private DFP1Mapper dfp1Mapper;
    @Autowired
    private DFP2Mapper dfp2Mapper;
    @Autowired
    private DFP3Mapper dfp3Mapper;
    @Autowired
    private DFP4Mapper dfp4Mapper;
    @Autowired
    private DFP5Mapper dfp5Mapper;

    @GetMapping("/print-data1")
    public ResponseEntity<?> printData1(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            Timestamp startTime = Timestamp.valueOf(startDate.atStartOfDay());
            Timestamp endTime = Timestamp.valueOf(endDate.atTime(LocalTime.MAX));

            List<DFP1> dfp1s = dfp1Mapper.findByUpdateTimeBetween(startTime, endTime);

            return SimpleExcelExporter.exportToExcel(dfp1s, "DFP1 Production Data");
        }catch (IOException e) {
            return ResponseEntity.internalServerError().body("导出失败: " + e.getMessage());
        }
    }

    @GetMapping("/print-data2")
    public ResponseEntity<?> printData2(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            Timestamp startTime = Timestamp.valueOf(startDate.atStartOfDay());
            Timestamp endTime = Timestamp.valueOf(endDate.atTime(LocalTime.MAX));

            List<DFP2> dfp2s = dfp2Mapper.findByUpdateTimeBetween(startTime, endTime);

            return SimpleExcelExporter.exportToExcel(dfp2s, "DFP2 Production Data");
        }catch (IOException e) {
            return ResponseEntity.internalServerError().body("导出失败: " + e.getMessage());
        }
    }

    @GetMapping("/print-data3")
    public ResponseEntity<?> printData3(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            Timestamp startTime = Timestamp.valueOf(startDate.atStartOfDay());
            Timestamp endTime = Timestamp.valueOf(endDate.atTime(LocalTime.MAX));

            List<DFP3> dfp3s = dfp3Mapper.findByUpdateTimeBetween(startTime, endTime);

            return SimpleExcelExporter.exportToExcel(dfp3s, "DFP3 Production Data");
        }catch (IOException e) {
            return ResponseEntity.internalServerError().body("导出失败: " + e.getMessage());
        }
    }

    @GetMapping("/print-data4")
    public ResponseEntity<?> printData4(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            Timestamp startTime = Timestamp.valueOf(startDate.atStartOfDay());
            Timestamp endTime = Timestamp.valueOf(endDate.atTime(LocalTime.MAX));

            List<DFP4> dfp4s = dfp4Mapper.findByUpdateTimeBetween(startTime, endTime);

            return SimpleExcelExporter.exportToExcel(dfp4s, "DFP4 Production Data");
        }catch (IOException e) {
            return ResponseEntity.internalServerError().body("导出失败: " + e.getMessage());
        }
    }

    @GetMapping("/print-data5")
    public ResponseEntity<?> printData5(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            Timestamp startTime = Timestamp.valueOf(startDate.atStartOfDay());
            Timestamp endTime = Timestamp.valueOf(endDate.atTime(LocalTime.MAX));

            List<DFP5> dfp5s = dfp5Mapper.findByUpdateTimeBetween(startTime, endTime);

            return SimpleExcelExporter.exportToExcel(dfp5s, "DFP5 Production Data");
        }catch (IOException e) {
            return ResponseEntity.internalServerError().body("导出失败: " + e.getMessage());
        }
    }
}
