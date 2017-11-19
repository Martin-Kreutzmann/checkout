package com.idealo.task.checkout.price;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public final class PricingRule {

  private String sku;
  private int unitPrice;
  private SpecialPrice specialPrice;

  public PricingRule(String sku, int unitPrice, SpecialPrice specialPrice) {
    Preconditions.checkArgument(unitPrice >= 0, "No negative unitPrice allowed.");
    Preconditions.checkArgument(!Strings.isNullOrEmpty(sku), "Invalid sku.");
    this.sku = sku;
    this.unitPrice = unitPrice;
    this.specialPrice = specialPrice;
  }

  String getSku() {
    return sku;
  }

  int calculatePrice(int amount) {
    Preconditions.checkArgument(amount >= 0, "No negative amounts allowed.");
    int remainingAmount = amount;
    int totalPrice = 0;

    if (specialPrice != null) {
      SpecialPrice.CalculationResult specialPriceCalculationResult = specialPrice.calculatePrice(amount);
      remainingAmount = specialPriceCalculationResult.getRemainingAmount();
      totalPrice += specialPriceCalculationResult.getTotalPrice();
    }

    totalPrice += remainingAmount * unitPrice;
    return totalPrice;
  }

}
