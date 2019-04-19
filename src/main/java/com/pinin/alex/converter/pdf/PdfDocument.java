package com.pinin.alex.converter.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pinin.alex.converter.exception.ConversionException;
import com.pinin.alex.style.EtMargin;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

import java.io.IOException;
import java.io.OutputStream;

class PdfDocument {

    @Getter(value = AccessLevel.PROTECTED)
    private final Document document;

    private final OutputStream outputStream;

    PdfDocument(@NonNull EtMargin documentMargin, @NonNull OutputStream out) {
        outputStream = out;
        document = new Document(PageSize.A4, documentMargin.getLeft(), documentMargin.getRight(),
                documentMargin.getTop(), documentMargin.getBottom());
        try {
            PdfWriter.getInstance(document, out);
        } catch (DocumentException e) {
            throw new ConversionException("Can not create pdf document", e);
        }
        document.open();
    }

    void add(@NonNull PdfPTable pdfPTable) {
        try {
            document.add(pdfPTable);
        } catch (DocumentException e) {
            throw new ConversionException(String.format("Can not add element %s to pdf document", pdfPTable), e);
        }
    }

    void close() {
        document.close();
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new ConversionException("Can not finish pdf document creation", e);
        }
    }
}
