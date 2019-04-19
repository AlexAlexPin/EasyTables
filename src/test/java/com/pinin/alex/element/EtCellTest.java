package com.pinin.alex.element;

import com.pinin.alex.style.EtBorderStyle;
import com.pinin.alex.style.EtFontStyle;
import com.pinin.alex.style.EtHAlignment;
import com.pinin.alex.style.EtVAlignment;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EtCellTest {

    @Test
    public void constructorCreatesDefaultCell() {
        EtCell cell = new EtCell();

        assertThat(cell.getValue(), is(""));
        assertThat(cell.getRowSpan(), is(1));
        assertThat(cell.getColSpan(), is(1));
        assertThat(cell.getFontSize(), is(10));
        assertThat(cell.getFontStyle(), is(EtFontStyle.NORMAL));
        assertThat(cell.getBorderStyle(), is(EtBorderStyle.ALL));
        assertThat(cell.getHAlignment(), is(EtHAlignment.CENTER));
        assertThat(cell.getVAlignment(), is(EtVAlignment.MIDDLE));
    }
}
