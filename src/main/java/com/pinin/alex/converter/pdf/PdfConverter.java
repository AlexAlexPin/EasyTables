package com.pinin.alex.converter.pdf;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.pinin.alex.converter.common.Converter;
import com.pinin.alex.element.EtCell;
import com.pinin.alex.element.EtRow;
import com.pinin.alex.element.EtTable;
import lombok.NonNull;

import java.io.OutputStream;
import java.util.List;

class PdfConverter implements Converter {

    private final PdfDocumentFactory pdfDocumentFactory;
    private final PdfTableFactory pdfTableFactory;
    private final PdfCellFactory pdfCellFactory;

    PdfConverter(
            @NonNull PdfDocumentFactory pdfDocumentFactory,
            @NonNull PdfTableFactory pdfTableFactory,
            @NonNull PdfCellFactory pdfCellFactory) {
        this.pdfDocumentFactory = pdfDocumentFactory;
        this.pdfTableFactory = pdfTableFactory;
        this.pdfCellFactory = pdfCellFactory;
    }

    @Override
    public void convert(@NonNull List<EtTable> etTables, @NonNull OutputStream out) {
        PdfDocument document = pdfDocumentFactory.getDocument(out);
        for (EtTable etTable : etTables) {
            PdfPTable table = pdfTableFactory.getTable(etTable);
            for (EtRow etRow : etTable.getRows()) {
                for (EtCell etCell : etRow.getCells()) {
                    PdfPCell cell = pdfCellFactory.getCell(etCell);
                    table.addCell(cell);
                }
            }
            document.add(table);
        }
        document.close();
    }
}
