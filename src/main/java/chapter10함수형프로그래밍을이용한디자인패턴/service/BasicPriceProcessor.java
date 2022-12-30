package chapter10함수형프로그래밍을이용한디자인패턴.service;

import chapter10함수형프로그래밍을이용한디자인패턴.model.Price;

public class BasicPriceProcessor implements PriceProcessor {
    @Override
    public Price process(Price price) {
        return price;
    }
}
