package com.pinin.alex.converter.xls;

import com.pinin.alex.converter.common.Converter;
import com.pinin.alex.element.EtCell;
import com.pinin.alex.element.EtRow;
import com.pinin.alex.element.EtTable;
import lombok.NonNull;
import org.apache.poi.hssf.usermodel.HSSFRow;

import java.io.OutputStream;
import java.util.List;

class XlsConverter implements Converter {

    private final XlsDocumentFactory xlsDocumentFactory;
    private final XlsRowFactory xlsRowFactory;
    private final XlsCellFactory xlsCellFactory;

    XlsConverter(
            @NonNull XlsDocumentFactory xlsDocumentFactory,
            @NonNull XlsRowFactory xlsRowFactory,
            @NonNull XlsCellFactory xlsCellFactory) {
        this.xlsDocumentFactory = xlsDocumentFactory;
        this.xlsRowFactory = xlsRowFactory;
        this.xlsCellFactory = xlsCellFactory;
    }

    @Override
    public void convert(@NonNull List<EtTable> etTables, @NonNull OutputStream out) {
        XlsDocument xlsDocument = xlsDocumentFactory.getDocument(out);
        int rowNum = 0;
        for (EtTable etTable : etTables)
        {
            for (EtRow etRow : etTable.getRows())
            {
                HSSFRow row = xlsRowFactory.getRow(xlsDocument, rowNum);
                int colNum = 0;
                for (EtCell etCell : etRow.getCells())
                {
                    xlsCellFactory.getCell(etCell, row, rowNum, colNum);
                    colNum++;
                }
                rowNum++;
            }
        }
        xlsDocument.close();
    }
}
