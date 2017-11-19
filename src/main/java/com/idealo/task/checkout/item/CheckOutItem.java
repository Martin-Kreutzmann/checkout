package com.idealo.task.checkout.item;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public final class CheckOutItem {
  private String sku;

  private int amount;

  public CheckOutItem(String sku, int amount) {
    Preconditions.checkArgument(amount >= 0, "No negative amount allowed.");
    Preconditions.checkArgument(!Strings.isNullOrEmpty(sku), "Invalid sku.");
    this.sku = sku;
    this.amount = amount;
  }

  public String getSku() {
    return sku;
  }

  public int getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return "CheckOutItem{" +
        "sku='" + sku + '\'' +
        ", amount=" + amount +
        '}';
  }
}
