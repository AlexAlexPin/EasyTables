package com.pinin.alex.converter.xls;

import com.pinin.alex.element.EtCell;
import com.pinin.alex.element.EtRow;
import com.pinin.alex.element.EtTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.OutputStream;
import java.util.Collections;

import static com.pinin.alex.converter.testutils.TestHelper.once;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.apache.poi.hssf.usermodel.*")
public class XlsConverterTest {

    @Test
    public void convertExecutesAllConvertingSteps() {
        int rowNum = 0;
        int conNum = 0;

        EtCell etCell = new EtCell();
        EtRow etRow = new EtRow().add(etCell);
        EtTable etTable = new EtTable(1).add(etRow);
        OutputStream outputStream = mock(OutputStream.class);

        XlsTestHelper.XlsMockSet mockSet = XlsTestHelper.createXlsMockSet(conNum);

        XlsDocument xlsDocument = mock(XlsDocument.class);
        XlsDocumentFactory xlsDocumentFactory = mock(XlsDocumentFactory.class);
        when(xlsDocumentFactory.getDocument(outputStream))
                .thenReturn(xlsDocument);

        XlsRowFactory xlsRowFactory = mock(XlsRowFactory.class);
        when(xlsRowFactory.getRow(xlsDocument, rowNum))
                .thenReturn(mockSet.getRow());

        XlsCellFactory xlsCellFactory = mock(XlsCellFactory.class);


        XlsConverter systemUnderTest = new XlsConverter(xlsDocumentFactory, xlsRowFactory, xlsCellFactory);
        systemUnderTest.convert(Collections.singletonList(etTable), outputStream);


        verify(xlsCellFactory, once()).getCell(etCell, mockSet.getRow(), rowNum, conNum);
        verify(xlsDocument, once()).close();
    }
}
