package com.pinin.alex.converter.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.pinin.alex.style.EtMargin;
import org.junit.Test;

import java.io.OutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class PdfDocumentTest {

    @Test
    public void constructorCreatesDocumentWithPassedParameters() {
        float left = 0;
        float top = 1;
        float right = 2;
        float bottom = 3;
        EtMargin margin = EtMargin.builder()
                .left(left).top(top).right(right).bottom(bottom).build();


        PdfDocument testResult = new PdfDocument(margin, mock(OutputStream.class));


        Document document = testResult.getDocument();
        assertThat(document.leftMargin(), is(left));
        assertThat(document.topMargin(), is(top));
        assertThat(document.rightMargin(), is(right));
        assertThat(document.bottomMargin(), is(bottom));
        assertThat(document.bottomMargin(), is(bottom));
        assertThat(document.getPageSize(), is(PageSize.A4));
        assertThat(document.isOpen(), is(true));
    }
}
