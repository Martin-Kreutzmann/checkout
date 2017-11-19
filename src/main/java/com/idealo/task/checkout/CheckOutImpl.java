package com.idealo.task.checkout;

import com.google.common.base.Strings;
import com.idealo.task.checkout.item.CheckOutItem;
import com.idealo.task.checkout.price.PricingRuleSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;

public class CheckOutImpl implements CheckOut {

  private PricingRuleSet pricingRuleSet;

  private Map<String, Integer> itemsAmountMap = new HashMap<>();

  CheckOutImpl(PricingRuleSet pricingRuleSet) {
    Preconditions.checkNotNull(pricingRuleSet);
    this.pricingRuleSet = pricingRuleSet;
  }

  @Override
  public void scan(String item) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(item) && item.length() == 1, "Invalid item scanned.");
    addItemToAmountsMap(item);
  }

  @Override
  public int total() {
    List<CheckOutItem> checkOutItems = itemsAmountMap.entrySet()
        .stream()
        .map(entry -> new CheckOutItem(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
    return pricingRuleSet.generatePrice(checkOutItems);
  }

  private void addItemToAmountsMap(String item) {
    Integer newAmount = itemsAmountMap.containsKey(item) ? itemsAmountMap.get(item) + 1 : 1;
    itemsAmountMap.put(item, newAmount);
  }
}
