package com.pinin.alex.converter.pdf;

import com.itextpdf.text.pdf.PdfPCell;
import com.pinin.alex.element.EtCell;
import com.pinin.alex.style.EtVAlignment;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class PdfCellFactoryVerticalAlignmentTest {

    private final PdfCellFactory systemUnderTest = new PdfCellFactory(PdfCellFactory.getDefaultEncoding());

    @Test
    @Parameters({
            "TOP, 4",
            "MIDDLE, 5",
            "BOTTOM, 6"
    })
    public void getCellSetsVerticalAlignmentFromVAlignment(EtVAlignment vAlignment, int expectedVerticalAlignment) {
        EtCell etCell = new EtCell().setVAlignment(vAlignment);

        PdfPCell testResult = systemUnderTest.getCell(etCell);

        assertEquals(expectedVerticalAlignment, testResult.getVerticalAlignment());
    }
}
