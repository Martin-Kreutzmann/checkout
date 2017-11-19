package com.idealo.task.checkout.price;

import com.google.common.base.Preconditions;

public final class SpecialPrice {
  private int quantity;
  private int price;

  public SpecialPrice(int quantity, int price) {
    Preconditions.checkArgument(quantity >= 1, "No negative or zero quantity allowed.");
    Preconditions.checkArgument(price >= 0, "No negative price allowed.");
    this.quantity = quantity;
    this.price = price;
  }

  CalculationResult calculatePrice(int amount) {
    Preconditions.checkArgument(amount >= 0, "No negative amounts allowed.");
    int amountSpecialPriceApplies = amount / quantity;
    int totalPrice = amountSpecialPriceApplies * price;
    return new CalculationResult(totalPrice, amount % quantity);
  }

  static class CalculationResult {
    private int totalPrice;
    private int remainingAmount;

    CalculationResult(int totalPrice, int remainingAmount) {
      this.totalPrice = totalPrice;
      this.remainingAmount = remainingAmount;
    }

    int getTotalPrice() {
      return totalPrice;
    }

    int getRemainingAmount() {
      return remainingAmount;
    }
  }
}
