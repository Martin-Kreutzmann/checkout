package com.idealo.task.checkout.price;

import com.idealo.task.checkout.item.CheckOutItem;

import java.util.List;

public interface PricingRuleSet {
  int generatePrice(List<CheckOutItem> items);
}
