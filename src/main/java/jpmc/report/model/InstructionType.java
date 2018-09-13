package jpmc.report.model;

/**
 * Created by Siddhant Sinha on 9/10/2018.
 */
public enum InstructionType {
    B("Buy"),S("Sell");

    private String name;

    public String getName() {
        return name;
    }

     InstructionType(String name){
        this.name=name;
    }
}
