package javabook.dao;

import javabook.pojo.Order;

/**
 * @author dankejun
 * @create 2020/12/3 22:37
 */
public interface OrderDao {
    int saveOrder(Order order);
}
