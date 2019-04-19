package com.pinin.alex.converter.pdf;

import com.itextpdf.text.pdf.PdfPTable;
import com.pinin.alex.element.EtTable;
import lombok.NonNull;

class PdfTableFactory {

    PdfPTable getTable(@NonNull EtTable etTable) {
        PdfPTable pdfPTable = new PdfPTable(etTable.getColumnAmount());
        pdfPTable.setWidthPercentage(100);
        return pdfPTable;
    }
}
