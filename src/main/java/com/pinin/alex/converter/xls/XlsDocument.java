package com.pinin.alex.converter.xls;

import com.pinin.alex.converter.exception.ConversionException;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;

class XlsDocument {

    @Getter(value = AccessLevel.PACKAGE)
    private final HSSFWorkbook workbook;

    @Getter(value = AccessLevel.PACKAGE)
    private final HSSFSheet sheet;

    private final OutputStream outputStream;

    XlsDocument(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.workbook = new HSSFWorkbook();
        this.sheet = workbook.createSheet();
    }

    void close() {
        try {
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            throw new ConversionException("Can not finish xls document creation", e);
        }
    }
}
