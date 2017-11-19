package com.idealo.task.checkout.price;

import com.google.common.base.Preconditions;
import com.idealo.task.checkout.item.CheckOutItem;

import java.util.*;
import java.util.stream.Collectors;

public final class PricingRuleSetImpl implements PricingRuleSet {
  private final Map<String, PricingRule> ruleSet;

  public PricingRuleSetImpl(List<PricingRule> ruleSet) {
    this.ruleSet = ruleSet.stream()
        .collect(Collectors.toMap(PricingRule::getSku, rule -> rule));
  }

  public int generatePrice(List<CheckOutItem> items) {
    Preconditions.checkNotNull(items, "List of CheckOutItem cant be null.");
    return items.stream()
        .map(this::getPrice)
        .reduce(Integer::sum)
        .orElse(0);
  }

  private int getPrice(CheckOutItem item) {
    Preconditions.checkNotNull("Invalid item.");
    PricingRule rule = ruleSet.get(item.getSku());

    if(rule == null) {
      throw new IllegalArgumentException("No pricing rule found for item '" + item + "'.");
    }

    return rule.calculatePrice(item.getAmount());
  }

}
