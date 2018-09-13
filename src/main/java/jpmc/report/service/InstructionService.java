package jpmc.report.service;

import java.util.Map;

import jpmc.report.model.InstructionData;


/**
 * Created by Siddhant Sinha on 9/11/2018.
 */
public interface InstructionService {

    void addInstructionData(InstructionData data);
    
    Map getDateWiseMappingOfTradeData();
	

}
