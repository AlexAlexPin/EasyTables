package com.pinin.alex.converter.xls;

import com.pinin.alex.element.EtCell;
import com.pinin.alex.style.EtVAlignment;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.poi.ss.usermodel.VerticalAlignment;
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
public class XlsCellFactoryVerticalAlignmentTest {

    private final XlsCellFactory systemUnderTest = new XlsCellFactory();

    @Test
    @Parameters({
            "TOP,    TOP",
            "MIDDLE, CENTER",
            "BOTTOM, BOTTOM"
    })
    public void getCellSetsVerticalAlignmentFromVAlignment(EtVAlignment vAlignment, VerticalAlignment expected) {
        int colNum = 1;
        int rowNum = 1;
        XlsTestHelper.XlsMockSet mockSet = XlsTestHelper.createXlsMockSet(colNum);

        EtCell etCell = new EtCell().setVAlignment(vAlignment);


        systemUnderTest.getCell(etCell, mockSet.getRow(), rowNum, colNum);


        verify(mockSet.getCellStyle(), once()).setVerticalAlignment(expected);
    }
}
