package com.pinin.alex.element;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EtTableTest {

    @Test
    public void constructorCreatesBasicTable() {
        int columnAmount = 2;

        EtTable table = new EtTable(columnAmount);

        assertThat(table.getRows().size(), is(0));
        assertThat(table.getColumnAmount(), is(columnAmount));
        assertThat(table.getColumnWidths().length, is(columnAmount));
        for (int columnWidth : table.getColumnWidths()) {
            assertThat(columnWidth, is(1));
        }
    }

    @Test
    public void addAddsOneRow() {
        EtTable table = new EtTable(1).add(new EtRow().add(new EtCell()));

        assertThat(table.getRows().size(), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addThrowsExceptionIfRowColumnsAmountIsNotSameAsInTable() {
        new EtTable(1).add(new EtRow());
    }

    @Test
    public void addAllAddsSeveralRows() {
        EtRow row = new EtRow().add(new EtCell());
        List<EtRow> rows = Arrays.asList(row, row);

        EtTable table = new EtTable(1).addAll(rows);

        assertThat(table.getRows().size(), is(rows.size()));
    }

    @Test
    public void setColumnWidthSetsNewColumnWidth() {
        int newColumnWidth = 2;
        EtTable table = new EtTable(1).setColumnWidth(0, newColumnWidth);

        assertThat(table.getColumnWidths()[0], is(newColumnWidth));
    }

    @Test
    public void setColumnWidthsSetsNewColumnWidths() {
        int[] newColumnWidths = new int[]{2};
        EtTable table = new EtTable(newColumnWidths.length).setColumnWidths(newColumnWidths);

        for (int i = 0; i < table.getColumnWidths().length; i++) {
            assertThat(table.getColumnWidths()[i], is(newColumnWidths[i]));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void setColumnWidthsThrowsExceptionIfNewColumnWidthsSizeIsNotSameAsInTable() {
        int[] newColumnWidths = new int[]{2};
        new EtTable(newColumnWidths.length + 1).setColumnWidths(newColumnWidths);
    }
}
