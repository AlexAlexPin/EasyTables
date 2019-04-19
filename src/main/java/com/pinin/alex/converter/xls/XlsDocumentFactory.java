package com.pinin.alex.converter.xls;

import lombok.NonNull;

import java.io.OutputStream;

class XlsDocumentFactory {

    XlsDocument getDocument(@NonNull OutputStream out) {
        return new XlsDocument(out);
    }
}
