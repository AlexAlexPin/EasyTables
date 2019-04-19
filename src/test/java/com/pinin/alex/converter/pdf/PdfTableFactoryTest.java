package com.pinin.alex.converter.pdf;

import com.itextpdf.text.pdf.PdfPTable;
import com.pinin.alex.element.EtTable;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PdfTableFactoryTest {

    @Test
    public void getTableReturnsTableWithPassedParameters() {
        int numberOfColumns = 1;

        PdfPTable table = new PdfTableFactory().getTable(new EtTable(numberOfColumns));

        assertThat(table.getNumberOfColumns(), is(numberOfColumns));
        assertThat(table.getWidthPercentage(), is(100f));
    }
}
