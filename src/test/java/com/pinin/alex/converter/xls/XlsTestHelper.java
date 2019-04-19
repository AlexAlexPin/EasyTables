package com.pinin.alex.converter.xls;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.hssf.usermodel.*;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

final class XlsTestHelper {

    @Getter
    @AllArgsConstructor
    static class XlsMockSet {
        private final HSSFRow row;
        private final HSSFSheet sheet;
        private final HSSFWorkbook workbook;
        private final HSSFFont font;
        private final HSSFCellStyle cellStyle;
    }

    static XlsMockSet createXlsMockSet(int columnNumber) {
        HSSFRow row = mock(HSSFRow.class);
        when(row.createCell(columnNumber)).thenReturn(mock(HSSFCell.class));

        HSSFSheet sheet = mock(HSSFSheet.class);
        when(row.getSheet()).thenReturn(sheet);

        HSSFWorkbook workbook = mock(HSSFWorkbook.class);
        when(sheet.getWorkbook()).thenReturn(workbook);

        HSSFFont font = mock(HSSFFont.class);
        when(workbook.createFont()).thenReturn(font);

        HSSFCellStyle cellStyle = mock(HSSFCellStyle.class);
        when(workbook.createCellStyle()).thenReturn(cellStyle);

        return new XlsMockSet(row, sheet, workbook, font, cellStyle);
    }
}
