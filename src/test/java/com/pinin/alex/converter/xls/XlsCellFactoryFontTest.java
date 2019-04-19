package com.pinin.alex.converter.xls;

import com.pinin.alex.element.EtCell;
import com.pinin.alex.style.EtFontStyle;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import static com.pinin.alex.converter.testutils.TestHelper.once;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnitParamsRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.apache.poi.hssf.usermodel.*")
public class XlsCellFactoryFontTest {

    private final XlsCellFactory systemUnderTest = new XlsCellFactory();

    @Test
    @Parameters({       // bold   italic
            "NORMAL,       false, false",
            "BOLD,         true,  false",
            "ITALIC,       false, true",
            "BOLD_ITALIC,  true,  true"
    })
    public void getCellSetsFontFromFontStyle(EtFontStyle fontStyle, boolean expectedBold, boolean expectedItalic) {
        int colNum = 1;
        int rowNum = 1;
        XlsTestHelper.XlsMockSet mockSet = XlsTestHelper.createXlsMockSet(colNum);

        EtCell etCell = new EtCell().setFontStyle(fontStyle);


        systemUnderTest.getCell(etCell, mockSet.getRow(), rowNum, colNum);


        verify(mockSet.getFont(), once()).setBold(expectedBold);
        verify(mockSet.getFont(), once()).setItalic(expectedItalic);
    }
}
