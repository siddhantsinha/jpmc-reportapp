package jpmc.report.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import jpmc.report.model.Currency;
import jpmc.report.model.InstructionData;
import jpmc.report.model.InstructionType;

public class InstructionDaoImplTest {
	
	InstructionDao daoTest=new InstructionDaoImpl();

	@Test
	public void testAddInstructionData() throws ParseException {
		
		InstructionData data1= new InstructionData("foo", InstructionType.B, new BigDecimal("0.50"), Currency.SGP, LocalDate.of(2018, 1, 6), LocalDate.of(2018, 1, 6), 200, new BigDecimal("100.25"));
		daoTest.addInstructionData(data1);
		Assert.assertFalse(daoTest.getData().isEmpty());
	}

	@Test
	public void testGetData() throws ParseException {
		
		InstructionData data1= new InstructionData("foo", InstructionType.B, new BigDecimal("0.50"), Currency.SGP, LocalDate.of(2018, 1, 6), LocalDate.of(2018, 1, 6), 200, new BigDecimal("100.25"));
		daoTest.addInstructionData(data1);
		Assert.assertTrue(InstructionType.B == daoTest.getData().get(0).getTradeType());
	}

}
