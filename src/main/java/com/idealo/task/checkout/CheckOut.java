package com.idealo.task.checkout;

public interface CheckOut {
  void scan(String item);

  int total();
}
