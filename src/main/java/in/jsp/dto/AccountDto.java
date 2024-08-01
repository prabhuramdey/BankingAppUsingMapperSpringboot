package in.jsp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {

	private Integer id;
	private String accountHolderName;
	private Double balance;
}
