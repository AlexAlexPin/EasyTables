package com.pinin.alex;

import com.pinin.alex.converter.AllConverterTests;
import com.pinin.alex.element.AllElementTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllConverterTests.class,
        AllElementTests.class
})
public class AllTests {
}
