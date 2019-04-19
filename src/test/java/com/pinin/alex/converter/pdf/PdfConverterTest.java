
package com.pinin.alex.converter.pdf;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.pinin.alex.element.EtCell;
import com.pinin.alex.element.EtRow;
import com.pinin.alex.element.EtTable;
import org.junit.Test;

import java.io.OutputStream;
import java.util.Collections;

import static com.pinin.alex.converter.testutils.TestHelper.once;
import static org.mockito.Mockito.*;

public class PdfConverterTest {

    @Test
    public void convertExecutesAllConvertingSteps() {
        EtCell etCell = new EtCell();
        EtRow etRow = new EtRow().add(etCell);
        EtTable etTable = new EtTable(1).add(etRow);
        OutputStream outputStream = mock(OutputStream.class);

        PdfDocument pdfDocument = mock(PdfDocument.class);
        PdfDocumentFactory pdfDocumentFactory = mock(PdfDocumentFactory.class);
        when(pdfDocumentFactory.getDocument(outputStream))
                .thenReturn(pdfDocument);

        PdfPTable pdfPTable = mock(PdfPTable.class);
        PdfTableFactory pdfTableFactory = mock(PdfTableFactory.class);
        when(pdfTableFactory.getTable(etTable))
                .thenReturn(pdfPTable);

        PdfPCell pdfPCell = mock(PdfPCell.class);
        PdfCellFactory pdfCellFactory = mock(PdfCellFactory.class);
        when(pdfCellFactory.getCell(etCell))
                .thenReturn(pdfPCell);


        PdfConverter systemUnderTest = new PdfConverter(pdfDocumentFactory, pdfTableFactory, pdfCellFactory);
        systemUnderTest.convert(Collections.singletonList(etTable), outputStream);


        verify(pdfDocumentFactory, once()).getDocument(outputStream);
        verify(pdfTableFactory, once()).getTable(etTable);
        verify(pdfCellFactory, once()).getCell(etCell);
        verify(pdfPTable, once()).addCell(pdfPCell);
        verify(pdfDocument, once()).add(pdfPTable);
        verify(pdfDocument, once()).close();
    }
}
