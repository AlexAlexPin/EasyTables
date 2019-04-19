package com.pinin.alex.converter;

import com.pinin.alex.converter.pdf.AllPdfTests;
import com.pinin.alex.converter.xls.AllXlsTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllPdfTests.class,
        AllXlsTests.class
})
public class AllConverterTests {
}
