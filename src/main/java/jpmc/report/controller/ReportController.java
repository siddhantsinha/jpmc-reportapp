package jpmc.report.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jpmc.report.dao.InstructionDao;
import jpmc.report.dao.InstructionDaoImpl;
import jpmc.report.data.ReportData;
import jpmc.report.model.Currency;
import jpmc.report.model.InstructionData;
import jpmc.report.model.InstructionType;
import jpmc.report.service.InstructionService;
import jpmc.report.service.InstructionServiceImpl;

/**
 * Created by Siddhant Sinha on 9/12/2018.
 */
public class ReportController {

	InstructionService service = null;
	
	public ReportController(InstructionService service){
		this.service=service;
	}
	

	private BigDecimal calculateTotalUsdAmount(List<InstructionData> instructions) {
		BigDecimal totalAmount = BigDecimal.ZERO;
		for (InstructionData data : instructions) {
			totalAmount = totalAmount.add(data.getUsdAmount());
		}

		return totalAmount;
	}

	public Map<LocalDate, ReportData> generateCompleteReport() {
		Map<LocalDate, ReportData> mapOfReport = new HashMap<>();

		// Generate the Data Based o requirement from service
		Map<LocalDate, Map<InstructionType, List<InstructionData>>> mapOfDataByDay = service.getDateWiseMappingOfTradeData();
		for (Map.Entry<LocalDate, Map<InstructionType, List<InstructionData>>> entry : mapOfDataByDay.entrySet()) {
			Map<InstructionType, List<InstructionData>> mapOfInstrructionsForADay = entry.getValue();

			// Initialize report
			BigDecimal totalBuyValue = BigDecimal.ZERO;
			BigDecimal totalSellValue = BigDecimal.ZERO;
			List<String> entitiesRankingOutgoing = Collections.emptyList();
			List<String> entitiesRankingIncoming = Collections.emptyList();

			if (mapOfInstrructionsForADay.containsKey(InstructionType.B)) {
				
				// sort the data based on incoming and outgoing amount
				List<InstructionData> sortedBuy = mapOfInstrructionsForADay.get(InstructionType.B).stream()
						.sorted((data1, data2) -> (data2.getUsdAmount().compareTo(data1.getUsdAmount())))
						.collect(Collectors.toList());
				totalBuyValue=calculateTotalUsdAmount(sortedBuy);
				// get the sorted entities
				entitiesRankingOutgoing = sortedBuy.stream().map(InstructionData::getEntity)
						.collect(Collectors.toList());
			}

			if (mapOfInstrructionsForADay.containsKey(InstructionType.S)) {
				List<InstructionData> sortedSell = mapOfInstrructionsForADay.get(InstructionType.S).stream()
						.sorted((data1, data2) -> (data2.getUsdAmount().compareTo(data1.getUsdAmount())))
						.collect(Collectors.toList());
				totalSellValue=calculateTotalUsdAmount(sortedSell);
				entitiesRankingIncoming = sortedSell.stream().map(InstructionData::getEntity)
						.collect(Collectors.toList());
			}

			System.out.println("Date : " + entry.getKey() + "-> Total Incoming: " + totalBuyValue + " Total OutGoing: "
					+ totalSellValue);
			System.out.println("Entities Ranking based on outgoing: " + entitiesRankingOutgoing);
			System.out.println("Entities Ranking based on incoming: " + entitiesRankingIncoming);

			mapOfReport.put(entry.getKey(),
					new ReportData(totalBuyValue, totalSellValue, entitiesRankingIncoming, entitiesRankingOutgoing));
		}

		return mapOfReport;
	}

	public void addInstruction(InstructionData data){
		
		service.addInstructionData(data);
	}
	
	public static void main(String[] args) throws ParseException {
		InstructionDao dao=new InstructionDaoImpl();
		InstructionService service=new InstructionServiceImpl(dao);
		ReportController controller = new ReportController(service);
		generateTestData(controller);
		controller.generateCompleteReport();
	}

	private static void generateTestData(ReportController controller) throws ParseException {
		
		InstructionData data=new InstructionData("foo", InstructionType.B, new BigDecimal("0.50"), Currency.SAR, LocalDate.of(2018, 1, 01),
				LocalDate.of(2018, 01, 1), 200, new BigDecimal("100.25"));
		 controller.addInstruction(data);
		data=new InstructionData("foo", InstructionType.B, new BigDecimal("0.50"), Currency.SAR, LocalDate.of(2018, 1, 02),
				LocalDate.of(2018, 01, 3), 200, new BigDecimal("100.25"));
		controller.addInstruction(data);
		data=new InstructionData("bar", InstructionType.S, new BigDecimal("0.10"), Currency.SGP, LocalDate.of(2018, 1, 02),
				LocalDate.of(2018, 01, 3), 450, new BigDecimal("150.50"));
		controller.addInstruction(data);
		data=new InstructionData("koo", InstructionType.B, new BigDecimal("0.50"), Currency.SAR, LocalDate.of(2018, 1, 01),
				LocalDate.of(2018, 01, 4), 300, new BigDecimal("100.25"));
		controller.addInstruction(data);
		data=new InstructionData("foo1", InstructionType.S, new BigDecimal("0.40"), Currency.AED, LocalDate.of(2018, 1, 01),
				LocalDate.of(2018, 01, 5), 500, new BigDecimal("150.00"));
		controller.addInstruction(data);
		data=new InstructionData("bar1", InstructionType.S, new BigDecimal("0.50"), Currency.GBP, LocalDate.of(2018, 1, 03),
				LocalDate.of(2018, 01, 4), 100, new BigDecimal("100.50"));
		controller.addInstruction(data);
		data=new InstructionData("koo1", InstructionType.S, new BigDecimal("1"), Currency.USD, LocalDate.of(2018, 1, 02),
				LocalDate.of(2018, 01, 6), 100, new BigDecimal("50.00"));
		controller.addInstruction(data);
		data=new InstructionData("foo2", InstructionType.B, new BigDecimal("0.30"), Currency.EUR, LocalDate.of(2018, 1, 02),
				LocalDate.of(2018, 01, 7), 100, new BigDecimal("200.00"));
		controller.addInstruction(data);
		data=new InstructionData("bar2", InstructionType.S, new BigDecimal("0.50"), Currency.GBP, LocalDate.of(2018, 1, 02),
				LocalDate.of(2018, 01, 7), 100, new BigDecimal("1000.50"));
		controller.addInstruction(data);
		data=new InstructionData("koo2", InstructionType.B, new BigDecimal("0.10"), Currency.SGP, LocalDate.of(2018, 1, 03),
				LocalDate.of(2018, 01, 6), 100, new BigDecimal("10.00"));
		controller.addInstruction(data);
		data=new InstructionData("foo3", InstructionType.B, new BigDecimal("0.50"), Currency.GBP, LocalDate.of(2018, 1, 03),
				LocalDate.of(2018, 01, 6), 100, new BigDecimal("150.50"));
		controller.addInstruction(data);
		data=new InstructionData("bar3", InstructionType.S, new BigDecimal("0.30"), Currency.EUR, LocalDate.of(2018, 1, 03),
				LocalDate.of(2018, 01, 4), 100, new BigDecimal("1000.50"));
		controller.addInstruction(data);
	}
}
