package javabook.dao.impl;

import javabook.dao.OrderItemDao;
import javabook.pojo.OrderItem;

/**
 * @author dankejun
 * @create 2020/12/3 22:40
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) " +
                "values(?,?,?,?,?)";
        return update(sql, orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),
                orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
