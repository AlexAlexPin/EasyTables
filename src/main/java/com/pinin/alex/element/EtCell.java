package com.pinin.alex.element;

import com.pinin.alex.style.EtBorderStyle;
import com.pinin.alex.style.EtFontStyle;
import com.pinin.alex.style.EtHAlignment;
import com.pinin.alex.style.EtVAlignment;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class EtCell {
    private String value;
    private int rowSpan;
    private int colSpan;
    private int fontSize;
    private EtFontStyle fontStyle;
    private EtBorderStyle borderStyle;
    private EtHAlignment hAlignment;
    private EtVAlignment vAlignment;

    public EtCell() {
        this("");
    }

    public EtCell(@NonNull Object value) {
        this(value.toString());
    }

    public EtCell(@NonNull String value) {
        this.value = value;
        setRowSpan(1);
        setColSpan(1);
        setFontSize(10);
        setFontStyle(EtFontStyle.NORMAL);
        setBorderStyle(EtBorderStyle.ALL);
        setHAlignment(EtHAlignment.CENTER);
        setVAlignment(EtVAlignment.MIDDLE);
    }

    public EtCell setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
        return this;
    }

    public EtCell setColSpan(int colSpan) {
        this.colSpan = colSpan;
        return this;
    }

    public EtCell setFontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public EtCell setFontStyle(EtFontStyle fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public EtCell setBorderStyle(EtBorderStyle borderStyle) {
        this.borderStyle = borderStyle;
        return this;
    }

    public EtCell setHAlignment(EtHAlignment hAlignment) {
        this.hAlignment = hAlignment;
        return this;
    }

    public EtCell setVAlignment(EtVAlignment vAlignment) {
        this.vAlignment = vAlignment;
        return this;
    }
}
