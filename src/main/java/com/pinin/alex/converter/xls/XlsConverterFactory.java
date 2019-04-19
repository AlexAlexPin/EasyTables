package com.pinin.alex.converter.xls;

import com.pinin.alex.converter.common.Converter;

public class XlsConverterFactory {

    public Converter getXlsConverter() {
        XlsDocumentFactory xlsDocumentFactory = new XlsDocumentFactory();
        XlsRowFactory xlsRowFactory = new XlsRowFactory();
        XlsCellFactory xlsCellFactory = new XlsCellFactory();
        return new XlsConverter(xlsDocumentFactory, xlsRowFactory, xlsCellFactory);
    }
}
