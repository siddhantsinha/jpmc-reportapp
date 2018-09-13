package jpmc.report.dao;

import java.util.ArrayList;
import java.util.List;

import jpmc.report.model.InstructionData;

/**
 * Created by Siddhant Sinha on 9/12/2018.
 */
public class InstructionDaoImpl implements InstructionDao {

    private static List<InstructionData> instructions=new ArrayList<InstructionData>(1);

    public void addInstructionData(InstructionData data) {

        instructions.add(data);
    }

    public List<InstructionData> getData() {
        return instructions;
    }
}
