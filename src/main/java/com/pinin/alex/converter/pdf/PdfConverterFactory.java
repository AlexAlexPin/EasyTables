package com.pinin.alex.converter.pdf;

import com.pinin.alex.converter.common.Converter;
import com.pinin.alex.style.EtMargin;
import lombok.NonNull;

public class PdfConverterFactory {

    public static EtMargin getDefaultDocumentMargin() {
        return PdfDocumentFactory.getDefaultDocumentMargin();
    }

    public static String getDefaultEncoding() {
        return PdfCellFactory.getDefaultEncoding();
    }

    public Converter getPdfConverter() {
        return getPdfConverter(getDefaultDocumentMargin(), getDefaultEncoding());
    }

    public Converter getPdfConverter(@NonNull EtMargin documentMargin, @NonNull String encoding) {
        PdfDocumentFactory pdfDocumentFactory = new PdfDocumentFactory(documentMargin);
        PdfTableFactory pdfTableFactory = new PdfTableFactory();
        PdfCellFactory pdfCellFactory = new PdfCellFactory(encoding);
        return new PdfConverter(pdfDocumentFactory, pdfTableFactory, pdfCellFactory);
    }
}
