package org.oss.focussnip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.oss.focussnip.mapper.OrderMapper;
import org.oss.focussnip.model.Orders;
import org.oss.focussnip.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void insertOrder(Orders order) {
        orderMapper.insert(order);
    }

    @Override
    public int updateOrder(Orders order) {
        QueryWrapper<Orders> qw = new QueryWrapper<>();
        qw.eq("order_id", order.getOrderId());
        return orderMapper.update(order, qw);
    }

    @Override
    public void deleteOrderByOrderId(Long orderId) {
        QueryWrapper<Orders> qw = new QueryWrapper<>();
        qw.eq("order_id", orderId);
        orderMapper.delete(qw);
    }

    @Override
    public Orders findOrderById(Long orderId) {
        QueryWrapper<Orders> qw = new QueryWrapper<Orders>();
        qw.eq("order_id", orderId);
        return orderMapper.selectOne(qw);
    }

    @Override
    public Page<Orders> findOrdersByUsername(String username) {
        QueryWrapper<Orders> qw = new QueryWrapper<Orders>();
        qw.eq("username", username); // (当前页，每页显示多少条数据)
        return orderMapper.selectPage(new Page(1, 10), qw);
    }

    @Override
    public Page<Orders> findOrdersByDecriptionLike(String username, String key) {
        QueryWrapper<Orders> qw = new QueryWrapper<Orders>();
        qw.eq("username", username);
        qw.like("description", key);
        return orderMapper.selectPage(new Page<>(1, 10), qw);
    }
}
