package javabook.service.impl;

import javabook.dao.BookDao;
import javabook.dao.OrderDao;
import javabook.dao.OrderItemDao;
import javabook.dao.impl.BookDaoImpl;
import javabook.dao.impl.OrderDaoImpl;
import javabook.dao.impl.OrderItemDaoImpl;
import javabook.pojo.*;
import javabook.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author dankejun
 * @create 2020/12/3 22:47
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId,new Date(), cart.getTotalPrice(), 0,userId);
        orderDao.saveOrder(order);
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);

            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }

        cart.clear();
        return orderId;
    }
}
