package com.pinin.alex.converter.xls;

import com.pinin.alex.element.EtCell;
import com.pinin.alex.style.EtBorderStyle;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.poi.ss.usermodel.BorderStyle;
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
public class XlsCellFactoryBordersTest {

    private final XlsCellFactory systemUnderTest = new XlsCellFactory();

    @Test
    @Parameters({  // left  top   rig.  bot.
            "NONE,    NONE, NONE, NONE, NONE",
            "BOTTOM,  NONE, NONE, NONE, THIN",
            "ALL,     THIN, THIN, THIN, THIN"
    })
    public void getCellSetsBorderFromBorderStyle(EtBorderStyle borderStyle,
                                                 BorderStyle expectedBorderLeft,
                                                 BorderStyle expectedBorderTop,
                                                 BorderStyle expectedBorderRight,
                                                 BorderStyle expectedBorderBottom) {
        int colNum = 1;
        int rowNum = 1;
        XlsTestHelper.XlsMockSet mockSet = XlsTestHelper.createXlsMockSet(colNum);

        EtCell etCell = new EtCell().setBorderStyle(borderStyle);


        systemUnderTest.getCell(etCell, mockSet.getRow(), rowNum, colNum);


        verify(mockSet.getCellStyle(), once()).setBorderLeft(expectedBorderLeft);
        verify(mockSet.getCellStyle(), once()).setBorderTop(expectedBorderTop);
        verify(mockSet.getCellStyle(), once()).setBorderRight(expectedBorderRight);
        verify(mockSet.getCellStyle(), once()).setBorderBottom(expectedBorderBottom);
    }
}
