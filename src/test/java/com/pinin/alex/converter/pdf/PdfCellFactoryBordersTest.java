package com.pinin.alex.converter.pdf;

import com.itextpdf.text.pdf.PdfPCell;
import com.pinin.alex.element.EtCell;
import com.pinin.alex.style.EtBorderStyle;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class PdfCellFactoryBordersTest {

    private final PdfCellFactory systemUnderTest = new PdfCellFactory(PdfCellFactory.getDefaultEncoding());

    @Test
    @Parameters({
            "NONE, 0",
            "BOTTOM, 2",
            "ALL, 15"
    })
    public void getCellSetsBorderFromBorderStyle(EtBorderStyle borderStyle, int expectedBorder) {
        EtCell etCell = new EtCell().setBorderStyle(borderStyle);

        PdfPCell testResult = systemUnderTest.getCell(etCell);

        assertEquals(expectedBorder, testResult.getBorder());
    }
}
