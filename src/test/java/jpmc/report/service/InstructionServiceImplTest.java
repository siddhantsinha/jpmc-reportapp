package jpmc.report.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import jpmc.report.dao.InstructionDao;
import jpmc.report.dao.InstructionDaoImpl;
import jpmc.report.model.Currency;
import jpmc.report.model.InstructionData;
import jpmc.report.model.InstructionType;


public class InstructionServiceImplTest {
	
	

	@Test
	public void testAdd() throws ParseException {
		InstructionDao dao=new InstructionDaoImpl();
		InstructionService serviceTest=new InstructionServiceImpl(dao);
		serviceTest.addInstructionData(new InstructionData("foo", InstructionType.B, new BigDecimal("0.50"), Currency.SGP, LocalDate.of(2018, 1, 6), LocalDate.of(2018, 1, 6), 200, new BigDecimal("100.25")));
		serviceTest.addInstructionData(new InstructionData("bar", InstructionType.S, new BigDecimal("0.22"), Currency.AED, LocalDate.of(2018, 1, 6), LocalDate.of(2018, 1, 6), 450, new BigDecimal("150.50")));
		serviceTest.addInstructionData(new InstructionData("koo", InstructionType.B, new BigDecimal("0.50"), Currency.SGP, LocalDate.of(2018, 1, 6), LocalDate.of(2018, 1, 6), 300, new BigDecimal("100.25")));
		serviceTest.addInstructionData(new InstructionData("Zer", InstructionType.S, new BigDecimal("0.22"), Currency.AED, LocalDate.of(2018, 1, 6), LocalDate.of(2018, 1, 6), 500, new BigDecimal("150.50")));

		Assert.assertFalse(serviceTest.getDateWiseMappingOfTradeData().isEmpty());
		
	}

	@Test
	public void testGenerateReport() throws ParseException {
		InstructionDao dao=new InstructionDaoImpl();
		InstructionService serviceTest=new InstructionServiceImpl(dao);
		serviceTest.addInstructionData(new InstructionData("foo", InstructionType.B, new BigDecimal("0.50"), Currency.SGP, LocalDate.of(2018, 1, 6), LocalDate.of(2018, 1, 6), 200, new BigDecimal("100.25")));
		serviceTest.addInstructionData(new InstructionData("bar", InstructionType.S, new BigDecimal("0.22"), Currency.AED, LocalDate.of(2018, 1, 6), LocalDate.of(2018, 1, 6), 450, new BigDecimal("150.50")));
		serviceTest.addInstructionData(new InstructionData("koo", InstructionType.B, new BigDecimal("0.50"), Currency.SGP, LocalDate.of(2018, 1, 6), LocalDate.of(2018, 1, 6), 300, new BigDecimal("100.25")));
		serviceTest.addInstructionData(new InstructionData("Zer", InstructionType.S, new BigDecimal("0.22"), Currency.AED, LocalDate.of(2018, 1, 6), LocalDate.of(2018, 1, 6), 500, new BigDecimal("150.50")));

		Assert.assertTrue(serviceTest.getDateWiseMappingOfTradeData().containsKey(LocalDate.of(2018, 1, 6)));
	}

}
