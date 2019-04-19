package com.pinin.alex.converter.pdf;

import com.pinin.alex.style.EtMargin;
import lombok.NonNull;

import java.io.OutputStream;

class PdfDocumentFactory {

    private final EtMargin documentMargin;

    static EtMargin getDefaultDocumentMargin() {
        return EtMargin.builder().left(20).top(20).right(20).bottom(20).build();
    }

    PdfDocumentFactory(@NonNull EtMargin documentMargin) {
        this.documentMargin = documentMargin;
    }

    PdfDocument getDocument(OutputStream out) {
        return new PdfDocument(documentMargin, out);
    }
}
