package jpmc.report.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jpmc.report.dao.InstructionDao;
import jpmc.report.model.InstructionData;
import jpmc.report.model.InstructionType;

/**
 * Created by Siddhant Sinha on 9/12/2018.
 */
public class InstructionServiceImpl implements InstructionService {

	InstructionDao dao = null;
	
	public InstructionServiceImpl(InstructionDao dao){
		this.dao=dao;
	}
	

	public void addInstructionData(InstructionData data) {
		dao.addInstructionData(data);
	}

	private List<InstructionData> getData() {
		return dao.getData();
	}

	/**
	 *
	 * @return Map map of date with the buy and sell instructions
	 *
	 */
	public Map<LocalDate, Map> getDateWiseMappingOfTradeData() {

		Map<LocalDate, Map> instructionByDateMap = new HashMap<>();

		for (InstructionData data : getData()) {
			// create map of date wise trading based on actual settlement date
			instructionByDateMap.putIfAbsent(data.getActualSettlementDate(), new HashMap<>(2));
			// For each incoming and outgoing on a day add Instructions.
			Map<InstructionType, List<InstructionData>> instructionsByTradeType = instructionByDateMap
					.get(data.getActualSettlementDate());
			instructionsByTradeType.putIfAbsent(data.getTradeType(), new ArrayList<>());
			instructionsByTradeType.get(data.getTradeType()).add(data);

		}

		return instructionByDateMap;
	}


}
