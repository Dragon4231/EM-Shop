package com.effective.shop.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticateResponse {

    public Integer code;
    public String result;
}
