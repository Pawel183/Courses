package com.course.accounts.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountsDto {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
