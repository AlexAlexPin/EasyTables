package com.pinin.alex.converter.pdf;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PdfConverterTest.class,
        PdfDocumentTest.class,
        PdfCellFactoryComplexTest.class,
        PdfCellFactoryFontTest.class,
        PdfCellFactoryBordersTest.class,
        PdfCellFactoryHorizontalAlignmentTest.class,
        PdfCellFactoryVerticalAlignmentTest.class,
        PdfTableFactoryTest.class
})
public class AllPdfTests {
}
