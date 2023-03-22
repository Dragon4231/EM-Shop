package com.effective.shop.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopUpBalanceRequest {
    String username;
    Double addMoney;
}
