package com.pinin.alex.element;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EtTable {
    private final int columnAmount;
    private final List<EtRow> rows = new ArrayList<>();
    private int[] columnWidths;

    public EtTable(int columnAmount) {
        this.columnAmount = columnAmount;
        columnWidths = new int[columnAmount];
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = 1;
        }
    }

    public EtTable add(EtRow row) {
        if (row.getCells().size() != columnAmount) {
            String message = String.format("Expected %s columns, received %s", columnAmount, row.getCells().size());
            throw new IllegalArgumentException(message);
        }
        rows.add(row);
        return this;
    }

    public EtTable addAll(List<EtRow> row) {
        row.forEach(this::add);
        return this;
    }

    public EtTable setColumnWidth(int columnNumber, int width) {
        columnWidths[columnNumber] = width;
        return this;
    }

    public EtTable setColumnWidths(int... widths) {
        if (widths.length != columnWidths.length) {
            String message = String.format("Expected %s columns, received %s", columnWidths.length, widths.length);
            throw new IllegalArgumentException(message);
        }
        System.arraycopy(widths, 0, columnWidths, 0, columnWidths.length);
        return this;
    }
}
