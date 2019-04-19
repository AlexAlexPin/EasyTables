package com.pinin.alex.converter.xls;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        XlsCellFactoryBordersTest.class,
        XlsCellFactoryComplexTest.class,
        XlsCellFactoryFontTest.class,
        XlsCellFactoryHorizontalAlignmentTest.class,
        XlsCellFactoryVerticalAlignmentTest.class,
        XlsConverterTest.class
})
public class AllXlsTests {
}
