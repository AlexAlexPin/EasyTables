package com.pinin.alex.converter.pdf;

import com.itextpdf.text.pdf.PdfPCell;
import com.pinin.alex.element.EtCell;
import com.pinin.alex.style.EtFontStyle;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class PdfCellFactoryFontTest {

    private final PdfCellFactory systemUnderTest = new PdfCellFactory(PdfCellFactory.getDefaultEncoding());

    @Test
    @Parameters({
            "NORMAL, Free Sans",
            "BOLD, Free Sans Bold",
            "ITALIC, Free Sans Oblique",
            "BOLD_ITALIC, Free Sans Bold Oblique"
    })
    public void getCellSetsFontFromFontStyle(EtFontStyle fontStyle, String expectedFontName) {
        EtCell etCell = new EtCell().setFontStyle(fontStyle);

        PdfPCell testResult = systemUnderTest.getCell(etCell);

        String actualFontName = testResult.getPhrase().getFont().getBaseFont().getFullFontName()[0][3];
        assertEquals(expectedFontName, actualFontName);
    }
}
