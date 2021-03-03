package internet_store.integration.telegram.service;

import internet_store.core.domain.Order;
import internet_store.core.persistence.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FindOrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private FindOrderService findOrderService;

    @Test
    public void shouldReturn_Order() {
        Order order = new Order();
        order.setId(1L);
        order.setNumber("1");

        List<Order> orders = new ArrayList<>();
        orders.add(order);

        Mockito.when(orderRepository.findAllByNumber("1")).thenReturn(orders);

        Optional<Order> result = findOrderService.tryFindOrder("1");

        result.ifPresent(ord -> assertEquals("1", ord.getNumber()));
    }

    @Test
    public void twoOrdersWithSameNumbers_mustReturnNoError() {
        Order order = new Order();
        order.setId(1L);
        order.setNumber("1");

        Order order_2 = new Order();
        order_2.setId(2L);
        order_2.setNumber("1");

        List<Order> orders = new ArrayList<>();
        orders.add(order);
        orders.add(order_2);

        Mockito.when(orderRepository.findAllByNumber("1")).thenReturn(orders);

        Optional<Order> result = findOrderService.tryFindOrder("1");

        result.ifPresent(ord -> assertEquals(Optional.of(1L), Optional.of(ord.getId())));
        result.ifPresent(ord -> assertEquals("1", ord.getNumber()));
    }

    @Test
    public void OrderNoFound_ShouldReturnNull() {
        Mockito.when(orderRepository.findAllByNumber("1")).thenReturn(new ArrayList<>());

        Optional<Order> result = findOrderService.tryFindOrder("1");

        assertTrue(result.isEmpty());
    }
}