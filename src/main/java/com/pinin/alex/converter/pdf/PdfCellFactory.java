package com.pinin.alex.converter.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.pinin.alex.converter.exception.ConversionException;
import com.pinin.alex.element.EtCell;
import com.pinin.alex.style.EtBorderStyle;
import com.pinin.alex.style.EtFontStyle;
import com.pinin.alex.style.EtHAlignment;
import com.pinin.alex.style.EtVAlignment;
import lombok.NonNull;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

class PdfCellFactory {

    private final static Map<EtBorderStyle, Integer> BORDERS = new HashMap<>();
    private final static Map<EtHAlignment, Integer> H_ALIGNMENTS = new HashMap<>();
    private final static Map<EtVAlignment, Integer> V_ALIGNMENTS = new HashMap<>();
    private final Map<EtFontStyle, Font> FONTS = new HashMap<>();

    static {
        BORDERS.put(EtBorderStyle.NONE, Rectangle.NO_BORDER);
        BORDERS.put(EtBorderStyle.BOTTOM, Rectangle.BOTTOM);
        BORDERS.put(EtBorderStyle.ALL, Rectangle.BOX);

        H_ALIGNMENTS.put(EtHAlignment.LEFT, Element.ALIGN_LEFT);
        H_ALIGNMENTS.put(EtHAlignment.RIGHT, Element.ALIGN_RIGHT);
        H_ALIGNMENTS.put(EtHAlignment.CENTER, Element.ALIGN_CENTER);

        V_ALIGNMENTS.put(EtVAlignment.TOP, Element.ALIGN_TOP);
        V_ALIGNMENTS.put(EtVAlignment.BOTTOM, Element.ALIGN_BOTTOM);
        V_ALIGNMENTS.put(EtVAlignment.MIDDLE, Element.ALIGN_MIDDLE);
    }

    static String getDefaultEncoding() {
        return BaseFont.WINANSI;
    }

    PdfCellFactory(@NonNull String encoding) {
        FONTS.put(EtFontStyle.NORMAL, getFont("fonts/FreeSans.ttf", encoding));
        FONTS.put(EtFontStyle.BOLD, getFont("fonts/FreeSansBold.ttf", encoding));
        FONTS.put(EtFontStyle.ITALIC, getFont("fonts/FreeSansOblique.ttf", encoding));
        FONTS.put(EtFontStyle.BOLD_ITALIC, getFont("fonts/FreeSansBoldOblique.ttf", encoding));
    }

    PdfPCell getCell(@NonNull EtCell etCell) {
        Font font = FONTS.get(etCell.getFontStyle());
        font.setSize(etCell.getFontSize());
        PdfPCell pdfCell = new PdfPCell(new Phrase(etCell.getValue(), font));
        pdfCell.setRowspan(etCell.getRowSpan());
        pdfCell.setColspan(etCell.getColSpan());
        pdfCell.setBorder(BORDERS.get(etCell.getBorderStyle()));
        pdfCell.setHorizontalAlignment(H_ALIGNMENTS.get(etCell.getHAlignment()));
        pdfCell.setVerticalAlignment(V_ALIGNMENTS.get(etCell.getVAlignment()));
        return pdfCell;
    }

    private static Font getFont(String path, String encoding) {
        URL resource = PdfCellFactory.class.getClassLoader().getResource(path);
        if (resource == null) {
            throw new ConversionException(String.format("Font does not exist '%s'", path));
        }
        try {
            BaseFont baseFont = BaseFont.createFont(resource.getPath(), encoding, BaseFont.EMBEDDED);
            return new Font(baseFont);
        } catch (DocumentException | IOException e) {
            throw new ConversionException(String.format("Can not create font '%s'", path), e);
        }
    }
}
