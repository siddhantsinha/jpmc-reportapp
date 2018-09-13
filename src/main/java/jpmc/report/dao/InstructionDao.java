package jpmc.report.dao;

import java.util.List;

import jpmc.report.model.InstructionData;

/**
 * Created by Siddhant Sinha on 9/12/2018.
 */
public interface InstructionDao {

    void addInstructionData(InstructionData data);

    List<InstructionData> getData();

}
