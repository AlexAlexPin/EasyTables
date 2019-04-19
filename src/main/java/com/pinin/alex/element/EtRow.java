package com.pinin.alex.element;

import com.pinin.alex.style.EtBorderStyle;
import com.pinin.alex.style.EtFontStyle;
import com.pinin.alex.style.EtHAlignment;
import com.pinin.alex.style.EtVAlignment;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EtRow {
    private List<EtCell> cells = new ArrayList<>();

    public EtRow add(@NonNull EtCell cell) {
        cells.add(cell);
        return this;
    }

    public EtRow addAll(@NonNull List<EtCell> cells) {
        this.cells.addAll(cells);
        return this;
    }

    public EtRow setFontSize(int fontSize) {
        cells.forEach(c -> c.setFontSize(fontSize));
        return this;
    }

    public EtRow setFontStyle(EtFontStyle fontStyle) {
        cells.forEach(c -> c.setFontStyle(fontStyle));
        return this;
    }

    public EtRow setBorderStyle(EtBorderStyle borderStyle) {
        cells.forEach(c -> c.setBorderStyle(borderStyle));
        return this;
    }

    public EtRow setHAlignment(EtHAlignment hAlignment) {
        cells.forEach(c -> c.setHAlignment(hAlignment));
        return this;
    }

    public EtRow setVAlignment(EtVAlignment vAlignment) {
        cells.forEach(c -> c.setVAlignment(vAlignment));
        return this;
    }
}
