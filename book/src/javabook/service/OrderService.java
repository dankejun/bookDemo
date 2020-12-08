package javabook.service;

import javabook.pojo.Cart;

/**
 * @author dankejun
 * @create 2020/12/3 22:46
 */
public interface OrderService {
    String createOrder(Cart cart, Integer userId);
}
