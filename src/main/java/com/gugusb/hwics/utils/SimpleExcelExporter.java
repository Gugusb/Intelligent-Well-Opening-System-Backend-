package com.gugusb.hwics.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SimpleExcelExporter {

    public static ResponseEntity<Resource> exportToExcel(List<?> dataList, String sheetName) throws IOException {
        // 使用流式工作簿处理大数据量
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        Sheet sheet = workbook.createSheet(sheetName);

        // 获取数据类的字段
        Class<?> dataClass = dataList.isEmpty() ? Object.class : dataList.get(0).getClass();
        Field[] fields = dataClass.getDeclaredFields();

        // 创建标题行
        Row headerRow = sheet.createRow(0);
        int colNum = 0;
        for (Field field : fields) {
            if (!field.getName().equals("serialVersionUID")) {
                Cell cell = headerRow.createCell(colNum++);
                cell.setCellValue(convertFieldNameToHeader(field.getName()));
                cell.setCellStyle(createHeaderStyle(workbook));
            }
        }

        // 填充数据行
        int rowNum = 1;
        for (Object data : dataList) {
            Row row = sheet.createRow(rowNum++);
            colNum = 0;

            for (Field field : fields) {
                if (!field.getName().equals("serialVersionUID")) {
                    field.setAccessible(true);

                    Cell cell = row.createCell(colNum++);
                    try {
                        Object value = field.get(data);
                        if (value != null) {
                            if (value instanceof java.sql.Timestamp) {
                                cell.setCellValue(((java.sql.Timestamp) value).toLocalDateTime()
                                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                            } else {
                                cell.setCellValue(value.toString());
                            }
                        } else {
                            cell.setCellValue("");
                        }

                        cell.setCellStyle(createDataStyle(workbook));
                    } catch (IllegalAccessException e) {
                        cell.setCellValue("N/A");
                    }
                }
            }
        }

        // 自动调整列宽
//        for (int i = 0; i < fields.length - 1; i++) {
//            sheet.autoSizeColumn(i);
//        }

        // 将工作簿写入字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // 创建资源对象
        byte[] excelBytes = outputStream.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(excelBytes);

        // 创建响应头
        String filename = sheetName.toLowerCase().replace(" ", "_") + "_" + System.currentTimeMillis() + ".xlsx";

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename(filename).build().toString())
                .body(resource);
    }

    private static String convertFieldNameToHeader(String fieldName) {
        // 将字段名转换为更友好的标题
        StringBuilder result = new StringBuilder();
        for (String part : fieldName.split("(?=[A-Z])")) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(part.substring(0, 1).toUpperCase()).append(part.substring(1));
        }
        return result.toString();
    }

    private static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private static CellStyle createDataStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }
}
