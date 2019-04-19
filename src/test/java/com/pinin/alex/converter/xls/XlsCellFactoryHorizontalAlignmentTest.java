package com.pinin.alex.converter.xls;

import com.pinin.alex.element.EtCell;
import com.pinin.alex.style.EtHAlignment;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
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
public class XlsCellFactoryHorizontalAlignmentTest {

    private final XlsCellFactory systemUnderTest = new XlsCellFactory();

    @Test
    @Parameters({
            "LEFT,   LEFT",
            "CENTER, CENTER",
            "RIGHT,  RIGHT"
    })
    public void getCellSetsHorizontalAlignmentFromHAlignment(EtHAlignment hAlignment, HorizontalAlignment expected) {
        int colNum = 1;
        int rowNum = 1;
        XlsTestHelper.XlsMockSet mockSet = XlsTestHelper.createXlsMockSet(colNum);

        EtCell etCell = new EtCell().setHAlignment(hAlignment);


        systemUnderTest.getCell(etCell, mockSet.getRow(), rowNum, colNum);


        verify(mockSet.getCellStyle(), once()).setAlignment(expected);
    }
}
