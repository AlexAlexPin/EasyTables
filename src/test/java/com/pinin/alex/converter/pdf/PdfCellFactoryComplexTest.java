package com.pinin.alex.converter.pdf;

import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.pinin.alex.element.EtCell;
import com.pinin.alex.style.EtBorderStyle;
import com.pinin.alex.style.EtFontStyle;
import com.pinin.alex.style.EtHAlignment;
import com.pinin.alex.style.EtVAlignment;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PdfCellFactoryComplexTest {

    private final PdfCellFactory systemUnderTest = new PdfCellFactory(PdfCellFactory.getDefaultEncoding());

    @Test
    public void getCellSetsAllCellParameters() {
        EtCell etCell = new EtCell("Value")
                .setFontStyle(EtFontStyle.NORMAL)
                .setFontSize(1)
                .setRowSpan(2)
                .setColSpan(3)
                .setBorderStyle(EtBorderStyle.NONE)
                .setHAlignment(EtHAlignment.LEFT)
                .setVAlignment(EtVAlignment.TOP);


        PdfPCell testResult = systemUnderTest.getCell(etCell);


        assertThat(testResult.getPhrase().getChunks().get(0).getContent(), is("Value"));
        assertThat(testResult.getPhrase().getFont().getSize(), is(1f));
        assertThat(testResult.getPhrase().getFont().getFamilyname(), is("FreeSans"));
        assertThat(testResult.getRowspan(), is(2));
        assertThat(testResult.getColspan(), is(3));
        assertThat(testResult.getBorder(), is(Rectangle.NO_BORDER));
        assertThat(testResult.getHorizontalAlignment(), is(Element.ALIGN_LEFT));
        assertThat(testResult.getVerticalAlignment(), is(Element.ALIGN_TOP));
    }
}


