package com.pinin.alex;

import com.pinin.alex.converter.common.Converter;
import com.pinin.alex.converter.pdf.PdfConverterFactory;
import com.pinin.alex.converter.xls.XlsConverterFactory;
import com.pinin.alex.element.EtCell;
import com.pinin.alex.element.EtRow;
import com.pinin.alex.element.EtTable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EtTable table = new EtTable(2);
        table
                .add(new EtRow()
                        .add(new EtCell("1"))
                        .add(new EtCell("2")))
                .add(new EtRow()
                        .add(new EtCell("3").setColSpan(2))
                        .add(new EtCell("4")));

        Map<String, String> params = getParams(args);
        convert(params.get("--pdfout"), new PdfConverterFactory().getPdfConverter(), table);
        convert(params.get("--xlsout"), new XlsConverterFactory().getXlsConverter(), table);
    }

    private static void convert(String outPath, Converter converter, EtTable table) {
        if (outPath == null) {
            return;
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(outPath);
            converter.convert(Collections.singletonList(table), outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> getParams(String[] args) {
        Map<String, String> params = new HashMap<>();
        for (String arg : args) {
            if (!arg.startsWith("--")) {
                continue;
            }
            String[] split = arg.split("=");
            if (split.length != 2) {
                continue;
            }
            params.put(split[0].trim(), split[1].trim());
        }
        return params;
    }
}
