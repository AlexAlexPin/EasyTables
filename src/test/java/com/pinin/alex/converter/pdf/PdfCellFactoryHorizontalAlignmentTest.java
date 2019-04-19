package com.pinin.alex.converter.pdf;

import com.itextpdf.text.pdf.PdfPCell;
import com.pinin.alex.element.EtCell;
import com.pinin.alex.style.EtHAlignment;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class PdfCellFactoryHorizontalAlignmentTest {

    private final PdfCellFactory systemUnderTest = new PdfCellFactory(PdfCellFactory.getDefaultEncoding());

    @Test
    @Parameters({
            "LEFT, 0",
            "CENTER, 1",
            "RIGHT, 2"
    })
    public void getCellSetsHorizontalAlignmentFromHAlignment(EtHAlignment hAlignment, int expectedHorizontalAlignment) {
        EtCell etCell = new EtCell().setHAlignment(hAlignment);

        PdfPCell testResult = systemUnderTest.getCell(etCell);

        assertEquals(expectedHorizontalAlignment, testResult.getHorizontalAlignment());
    }
}
