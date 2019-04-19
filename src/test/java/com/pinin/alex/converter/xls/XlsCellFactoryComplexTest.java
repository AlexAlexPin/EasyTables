package com.pinin.alex.converter.xls;

import com.pinin.alex.element.EtCell;
import com.pinin.alex.style.EtBorderStyle;
import com.pinin.alex.style.EtFontStyle;
import com.pinin.alex.style.EtHAlignment;
import com.pinin.alex.style.EtVAlignment;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static com.pinin.alex.converter.testutils.TestHelper.once;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.apache.poi.hssf.usermodel.*")
public class XlsCellFactoryComplexTest {

    private final XlsCellFactory systemUnderTest = new XlsCellFactory();

    @Test
    public void getCellSetsAllCellParameters() {
        int colNum = 1;
        int rowNum = 1;
        XlsTestHelper.XlsMockSet mockSet = XlsTestHelper.createXlsMockSet(colNum);

        EtCell etCell = new EtCell("Value")
                .setFontStyle(EtFontStyle.NORMAL)
                .setFontSize(1)
                .setRowSpan(2)
                .setColSpan(3)
                .setBorderStyle(EtBorderStyle.NONE)
                .setHAlignment(EtHAlignment.LEFT)
                .setVAlignment(EtVAlignment.TOP);


        HSSFCell testCell = systemUnderTest.getCell(etCell, mockSet.getRow(), rowNum, colNum);


        verify(mockSet.getSheet(), once()).addMergedRegionUnsafe(argThat(new ArgumentMatcher<CellRangeAddress>() {
            @Override
            public boolean matches(Object argument) {
                CellRangeAddress cellRangeAddress = (CellRangeAddress) argument;
                return cellRangeAddress.getFirstRow() == rowNum
                        && cellRangeAddress.getLastRow() == rowNum + etCell.getRowSpan() - 1
                        && cellRangeAddress.getFirstColumn() == colNum
                        && cellRangeAddress.getLastColumn() == colNum + etCell.getColSpan() - 1;
            }
        }));

        verify(mockSet.getFont(), once()).setFontHeightInPoints((short) etCell.getFontSize());
        verify(mockSet.getFont(), once()).setBold(false);
        verify(mockSet.getFont(), once()).setItalic(false);

        verify(mockSet.getCellStyle(), once()).setFont(mockSet.getFont());
        verify(mockSet.getCellStyle(), once()).setAlignment(HorizontalAlignment.LEFT);
        verify(mockSet.getCellStyle(), once()).setVerticalAlignment(VerticalAlignment.TOP);
        verify(mockSet.getCellStyle(), once()).setWrapText(true);
        verify(mockSet.getCellStyle(), once()).setBorderTop(BorderStyle.NONE);
        verify(mockSet.getCellStyle(), once()).setBorderBottom(BorderStyle.NONE);
        verify(mockSet.getCellStyle(), once()).setBorderLeft(BorderStyle.NONE);
        verify(mockSet.getCellStyle(), once()).setBorderRight(BorderStyle.NONE);

        verify(testCell, once()).setCellStyle(mockSet.getCellStyle());
        verify(testCell, once()).setCellValue(etCell.getValue());
    }

    @Test
    public void getCellDoesNotSetMergedRegion() {
        int colNum = 1;
        int rowNum = 1;
        XlsTestHelper.XlsMockSet mockSet = XlsTestHelper.createXlsMockSet(colNum);

        EtCell etCell = new EtCell()
                .setRowSpan(1)
                .setColSpan(1);


        systemUnderTest.getCell(etCell, mockSet.getRow(), rowNum, colNum);


        verify(mockSet.getSheet(), never()).addMergedRegionUnsafe(any());
    }
}
