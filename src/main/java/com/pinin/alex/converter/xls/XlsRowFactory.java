package com.pinin.alex.converter.xls;

import lombok.NonNull;
import org.apache.poi.hssf.usermodel.HSSFRow;

class XlsRowFactory {

    HSSFRow getRow(@NonNull XlsDocument xlsDocument, int rowNum) {
        return xlsDocument.getSheet().createRow(rowNum);
    }
}
