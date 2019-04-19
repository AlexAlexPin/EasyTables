package com.pinin.alex.converter.xls;

import com.pinin.alex.element.EtCell;
import com.pinin.alex.style.EtBorderStyle;
import com.pinin.alex.style.EtFontStyle;
import com.pinin.alex.style.EtHAlignment;
import com.pinin.alex.style.EtVAlignment;
import lombok.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.HashMap;
import java.util.Map;

class XlsCellFactory {

    private final static Map<EtFontStyle, FontStyles> FONTS = new HashMap<>();
    private final static Map<EtBorderStyle, Borders> BORDERS = new HashMap<>();
    private final static Map<EtHAlignment, HorizontalAlignment> H_ALIGNMENTS = new HashMap<>();
    private final static Map<EtVAlignment, VerticalAlignment> V_ALIGNMENTS = new HashMap<>();

    static {
        FONTS.put(EtFontStyle.NORMAL, new FontStyles());
        FONTS.put(EtFontStyle.BOLD, FontStyles.builder()
                .bold(true)
                .build());
        FONTS.put(EtFontStyle.ITALIC, FontStyles.builder()
                .italic(true)
                .build());
        FONTS.put(EtFontStyle.BOLD_ITALIC, FontStyles.builder()
                .bold(true)
                .italic(true)
                .build());

        BORDERS.put(EtBorderStyle.NONE, new Borders());
        BORDERS.put(EtBorderStyle.BOTTOM, Borders.builder()
                .bottom(org.apache.poi.ss.usermodel.BorderStyle.THIN)
                .build());
        BORDERS.put(EtBorderStyle.ALL, Borders.builder()
                .left(org.apache.poi.ss.usermodel.BorderStyle.THIN)
                .top(org.apache.poi.ss.usermodel.BorderStyle.THIN)
                .right(org.apache.poi.ss.usermodel.BorderStyle.THIN)
                .bottom(org.apache.poi.ss.usermodel.BorderStyle.THIN)
                .build());

        H_ALIGNMENTS.put(EtHAlignment.LEFT, HorizontalAlignment.LEFT);
        H_ALIGNMENTS.put(EtHAlignment.RIGHT, HorizontalAlignment.RIGHT);
        H_ALIGNMENTS.put(EtHAlignment.CENTER, HorizontalAlignment.CENTER);

        V_ALIGNMENTS.put(EtVAlignment.TOP, VerticalAlignment.TOP);
        V_ALIGNMENTS.put(EtVAlignment.BOTTOM, VerticalAlignment.BOTTOM);
        V_ALIGNMENTS.put(EtVAlignment.MIDDLE, VerticalAlignment.CENTER);
    }

    HSSFCell getCell(@NonNull EtCell etCell, @NonNull HSSFRow row, int rowNum, int colNum) {
        if (etCell.getColSpan() > 1 || etCell.getRowSpan() > 1)
        {
            int lastRow = rowNum + etCell.getRowSpan() - 1;
            int lastCol = colNum + etCell.getColSpan() - 1;
            row.getSheet().addMergedRegionUnsafe(new CellRangeAddress(rowNum, lastRow, colNum, lastCol));
        }
        HSSFCell cell = row.createCell(colNum);
        HSSFCellStyle style = createCellStyle(row.getSheet().getWorkbook(), etCell);
        cell.setCellStyle(style);
        cell.setCellValue(etCell.getValue());
        return cell;
    }

    private HSSFCellStyle createCellStyle(HSSFWorkbook workbook, EtCell etCell)
    {
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) etCell.getFontSize());
        font.setFontName("Arial");

        FontStyles fontStyles = FONTS.get(etCell.getFontStyle());
        font.setBold(fontStyles.isBold());
        font.setItalic(fontStyles.isItalic());

        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(H_ALIGNMENTS.get(etCell.getHAlignment()));
        cellStyle.setVerticalAlignment(V_ALIGNMENTS.get(etCell.getVAlignment()));
        cellStyle.setWrapText(true);

        Borders borders = BORDERS.get(etCell.getBorderStyle());
        cellStyle.setBorderTop(borders.getTop());
        cellStyle.setBorderBottom(borders.getBottom());
        cellStyle.setBorderLeft(borders.getLeft());
        cellStyle.setBorderRight(borders.getRight());

        return cellStyle;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class FontStyles
    {
        private boolean bold;
        private boolean italic;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class Borders
    {
        @Builder.Default
        private org.apache.poi.ss.usermodel.BorderStyle left = org.apache.poi.ss.usermodel.BorderStyle.NONE;
        @Builder.Default
        private org.apache.poi.ss.usermodel.BorderStyle top = org.apache.poi.ss.usermodel.BorderStyle.NONE;
        @Builder.Default
        private org.apache.poi.ss.usermodel.BorderStyle right = org.apache.poi.ss.usermodel.BorderStyle.NONE;
        @Builder.Default
        private org.apache.poi.ss.usermodel.BorderStyle bottom = org.apache.poi.ss.usermodel.BorderStyle.NONE;
    }
}
