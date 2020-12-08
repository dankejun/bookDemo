package javabook.dao;

import javabook.pojo.OrderItem;

/**
 * @author dankejun
 * @create 2020/12/3 22:39
 */
public interface OrderItemDao {
    int saveOrderItem(OrderItem orderItem);
}
