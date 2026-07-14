package com.demotest.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardsDto {

    @NotEmpty(message = "MobileNumber can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "MobileNumber must be 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "CardNumber can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{12})",message = "CardNumber must be 12 digits")
    private String cardNumber;

    @NotEmpty(message = "Card type can not be a null or empty")
    private String cardType;


    @PositiveOrZero(message = "Total limit can't be negative.")
    private int totalLimit;

    @PositiveOrZero(message = "Amount used can't be negative.")
    private int amountUsed;

    @PositiveOrZero(message = "Available amount can't be negative.")
    private int availableAmount;


}
