package jpmc.report.data;

import java.math.BigDecimal;
import java.util.List;

public class ReportData {
	
	public ReportData(BigDecimal totalIncoming, BigDecimal totalOutgoing, List<String> sortedIncomingEntities,
			List<String> sortedOutgoingEntities) {
		super();
		this.totalIncoming = totalIncoming;
		this.totalOutgoing = totalOutgoing;
		this.sortedIncomingEntities = sortedIncomingEntities;
		this.sortedOutgoingEntities = sortedOutgoingEntities;
	}

	private BigDecimal totalIncoming;
	
    private BigDecimal totalOutgoing;
	
	private List<String> sortedIncomingEntities;
	
	private List<String> sortedOutgoingEntities;
	
	public BigDecimal getTotalIncoming() {
		return totalIncoming;
	}

	public BigDecimal getTotalOutgoing() {
		return totalOutgoing;
	}

	public List<String> getSortedIncomingEntities() {
		return sortedIncomingEntities;
	}

	public List<String> getSortedOutgoingEntities() {
		return sortedOutgoingEntities;
	}
	
	
	

}
