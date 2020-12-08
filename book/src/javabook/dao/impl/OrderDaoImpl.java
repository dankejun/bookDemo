package javabook.dao.impl;

import javabook.dao.OrderDao;
import javabook.pojo.Order;

/**
 * @author dankejun
 * @create 2020/12/3 22:38
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) " +
                "values(?,?,?,?,?)";
        return update(sql, order.getOrderId(),order.getCreateTime(),order.getPrice(),
                order.getStatus(),order.getUserId());
    }
}
