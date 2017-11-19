package com.idealo.task.checkout;

import com.idealo.task.checkout.price.PricingRule;
import com.idealo.task.checkout.price.PricingRuleSet;
import com.idealo.task.checkout.price.PricingRuleSetImpl;
import com.idealo.task.checkout.price.SpecialPrice;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PriceTest {

  @Test
  public void totals() {
    assertEquals(0, calculatePrice(""));
    assertEquals(40, calculatePrice("A"));
    assertEquals(90, calculatePrice("AB"));
    assertEquals(135, calculatePrice("CDBA"));
    assertEquals(80, calculatePrice("AA"));
    assertEquals(100, calculatePrice("AAA"));
    assertEquals(140, calculatePrice("AAAA"));
    assertEquals(180, calculatePrice("AAAAA"));
    assertEquals(200, calculatePrice("AAAAAA"));
    assertEquals(150, calculatePrice("AAAB"));
    assertEquals(180, calculatePrice("AAABB"));
    assertEquals(200, calculatePrice("AAABBD"));
    assertEquals(200, calculatePrice("DABABA"));
  }

  @Test
  public void incremental() {
    CheckOut co = new CheckOutImpl(createPricingRuleSet());
    assertEquals(0, co.total());
    co.scan("A");
    assertEquals(40, co.total());
    co.scan("B");
    assertEquals(90, co.total());
    co.scan("A");
    assertEquals(130, co.total());
    co.scan("A");
    assertEquals(150, co.total());
    co.scan("B");
    assertEquals(180, co.total());
  }

  public int calculatePrice(String goods) {
    CheckOut co = new CheckOutImpl(createPricingRuleSet());
    for (int i = 0; i < goods.length(); i++) {
      co.scan(String.valueOf(goods.charAt(i)));
    }
    return co.total();
  }

  private PricingRuleSet createPricingRuleSet() {
    List<PricingRule> pricingRuleList = new ArrayList<>();
    pricingRuleList.add(new PricingRule("A", 40, new SpecialPrice(3, 100)));
    pricingRuleList.add(new PricingRule("B", 50, new SpecialPrice(2, 80)));
    pricingRuleList.add(new PricingRule("C", 25, null));
    pricingRuleList.add(new PricingRule("D", 20, null));
    return new PricingRuleSetImpl(pricingRuleList);
  }
}
