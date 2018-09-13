package jpmc.report.model;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Created by Siddhant Sinha on 9/10/2018.
 */
public class InstructionData {

	private String entity;

	private InstructionType tradeType;

	private BigDecimal agreedFx;

	private Currency currency;

	private LocalDate instructionDate;

	private LocalDate settlementDate;

	private long units;

	private BigDecimal pricePerUnit;

	public InstructionData(String entity, InstructionType tradeType, BigDecimal agreedFx, Currency currency,
			LocalDate instructionDate, LocalDate settlementDate, long units, BigDecimal pricePerUnit) {
		this.entity = entity;
		this.tradeType = tradeType;
		this.agreedFx = agreedFx;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
	}

	public String getEntity() {
		return entity;
	}

	public InstructionType getTradeType() {
		return tradeType;
	}

	public void setTradeType(InstructionType tradeType) {
		this.tradeType = tradeType;
	}

	public BigDecimal getAgreedFx() {
		return agreedFx;
	}

	public Currency getCurrency() {
		return currency;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public long getUnits() {
		return units;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public BigDecimal getUsdAmount() {
		return this.agreedFx.multiply(this.pricePerUnit).multiply(BigDecimal.valueOf(this.units));
	}

	public LocalDate getActualSettlementDate() {
		LocalDate settlementDate=getSettlementDate();
		DayOfWeek dayOfWeek=settlementDate.getDayOfWeek();
		int daysBeforeWorkingDay = 0;
		boolean ifAedOrSar = currency==Currency.AED || currency==Currency.SAR;
		
		switch(dayOfWeek){		
		case FRIDAY:
			daysBeforeWorkingDay=ifAedOrSar?2:0;		
			break;
		case SATURDAY:
			daysBeforeWorkingDay=ifAedOrSar?1:2;
			break;
		case SUNDAY:
			daysBeforeWorkingDay=1;
			break;
		default:
			daysBeforeWorkingDay=0;
			
		}		
		settlementDate.plusDays(daysBeforeWorkingDay);
		return settlementDate;
	}

}
