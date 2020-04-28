package dto;

import java.math.BigInteger;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class OrderAndOrderDetailDto {
	private int oId;
	private Timestamp oCreateTime;
	private Float oPrice;
	private int oUserId;
	private int odId;
	private int odItemNum;
	private BigInteger odVersion;
	private int odItemId;
	private int odOrderId;
}
