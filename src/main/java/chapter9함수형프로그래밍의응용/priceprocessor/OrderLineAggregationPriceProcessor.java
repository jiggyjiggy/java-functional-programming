package chapter9함수형프로그래밍의응용.priceprocessor;

import chapter9함수형프로그래밍의응용.model.Order;
import chapter9함수형프로그래밍의응용.model.OrderLine;

import java.math.BigDecimal;
import java.util.function.Function;

public class OrderLineAggregationPriceProcessor implements Function<Order, Order> {

    @Override
    public Order apply(Order order) {
        return order.setAmount(order.getOrderLines().stream()
                .map(OrderLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

    }
}
