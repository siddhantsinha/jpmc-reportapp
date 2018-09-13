package jpmc.report.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import jpmc.report.dao.InstructionDao;
import jpmc.report.dao.InstructionDaoImpl;
import jpmc.report.data.ReportData;
import jpmc.report.model.Currency;
import jpmc.report.model.InstructionData;
import jpmc.report.model.InstructionType;
import jpmc.report.service.InstructionService;
import jpmc.report.service.InstructionServiceImpl;

public class ReportControllerTest {

	ReportController controller;

	@Test
	public void testGenerateReport() throws ParseException {
		InstructionDao dao=new InstructionDaoImpl();
		InstructionService service=new InstructionServiceImpl(dao);
		ReportController controller = new ReportController(service);

		generateTestData(controller);
		Map<LocalDate, ReportData> mapOfData = controller.generateCompleteReport();

		ReportData data = mapOfData.get(LocalDate.of(2018, 1, 06));
		Assert.assertArrayEquals(data.getSortedOutgoingEntities().toArray(), new String[] { "foo3", "koo2" });
		// Assert outgoing
		Assert.assertTrue(5000 == data.getTotalOutgoing().intValue());
		// Assert incoming amount
		Assert.assertTrue(7625 == data.getTotalIncoming().intValue());
	}

	private static void generateTestData(ReportController controller) throws ParseException {

		InstructionData data = new InstructionData("foo", InstructionType.B, new BigDecimal("0.50"), Currency.SAR,
				LocalDate.of(2018, 1, 01), LocalDate.of(2018, 01, 1), 200, new BigDecimal("100.25"));
		controller.addInstruction(data);
		data = new InstructionData("foo", InstructionType.B, new BigDecimal("0.50"), Currency.SAR,
				LocalDate.of(2018, 1, 02), LocalDate.of(2018, 01, 3), 200, new BigDecimal("100.25"));
		controller.addInstruction(data);
		data = new InstructionData("bar", InstructionType.S, new BigDecimal("0.10"), Currency.SGP,
				LocalDate.of(2018, 1, 02), LocalDate.of(2018, 01, 3), 450, new BigDecimal("150.50"));
		controller.addInstruction(data);
		data = new InstructionData("koo", InstructionType.B, new BigDecimal("0.50"), Currency.SAR,
				LocalDate.of(2018, 1, 01), LocalDate.of(2018, 01, 4), 300, new BigDecimal("100.25"));
		controller.addInstruction(data);
		data = new InstructionData("foo1", InstructionType.S, new BigDecimal("0.40"), Currency.AED,
				LocalDate.of(2018, 1, 01), LocalDate.of(2018, 01, 5), 500, new BigDecimal("150.00"));
		controller.addInstruction(data);
		data = new InstructionData("bar1", InstructionType.S, new BigDecimal("0.50"), Currency.GBP,
				LocalDate.of(2018, 1, 03), LocalDate.of(2018, 01, 4), 100, new BigDecimal("100.50"));
		controller.addInstruction(data);
		data = new InstructionData("koo1", InstructionType.S, new BigDecimal("1"), Currency.USD,
				LocalDate.of(2018, 1, 02), LocalDate.of(2018, 01, 6), 100, new BigDecimal("50.00"));
		controller.addInstruction(data);
		data = new InstructionData("foo2", InstructionType.B, new BigDecimal("0.30"), Currency.EUR,
				LocalDate.of(2018, 1, 02), LocalDate.of(2018, 01, 7), 100, new BigDecimal("200.00"));
		controller.addInstruction(data);
		data = new InstructionData("bar2", InstructionType.S, new BigDecimal("0.50"), Currency.GBP,
				LocalDate.of(2018, 1, 02), LocalDate.of(2018, 01, 7), 100, new BigDecimal("1000.50"));
		controller.addInstruction(data);
		data = new InstructionData("koo2", InstructionType.B, new BigDecimal("0.10"), Currency.SGP,
				LocalDate.of(2018, 1, 03), LocalDate.of(2018, 01, 6), 100, new BigDecimal("10.00"));
		controller.addInstruction(data);
		data = new InstructionData("foo3", InstructionType.B, new BigDecimal("0.50"), Currency.GBP,
				LocalDate.of(2018, 1, 03), LocalDate.of(2018, 01, 6), 100, new BigDecimal("150.50"));
		controller.addInstruction(data);
		data = new InstructionData("bar3", InstructionType.S, new BigDecimal("0.30"), Currency.EUR,
				LocalDate.of(2018, 1, 03), LocalDate.of(2018, 01, 4), 100, new BigDecimal("1000.50"));
		controller.addInstruction(data);
	}
}
