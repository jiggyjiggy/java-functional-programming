package chapter10함수형프로그래밍을이용한디자인패턴;

import chapter10함수형프로그래밍을이용한디자인패턴.model.Price;
import chapter10함수형프로그래밍을이용한디자인패턴.service.BasicPriceProcessor;
import chapter10함수형프로그래밍을이용한디자인패턴.service.DiscountPriceProcessor;
import chapter10함수형프로그래밍을이용한디자인패턴.service.PriceProcessor;
import chapter10함수형프로그래밍을이용한디자인패턴.service.TaxPriceProcessor;

public class Section3DecoratorPattern {
    /*
    * 구조 패턴의 하나
    * 용도에 따라 객체에 기능을 계속 추가(decorate)할 수 있게 해준다
    * */
    public static void main(String[] args) {
        Price unprocessedPrice = new Price("Original Price");

        PriceProcessor basicPriceProcessor = new BasicPriceProcessor();
        PriceProcessor discountPriceProcessor = new DiscountPriceProcessor();
        PriceProcessor taxPriceProcessor = new TaxPriceProcessor();

        PriceProcessor decoratedPriceProcessor = basicPriceProcessor
                .andThen(discountPriceProcessor)
                .andThen(taxPriceProcessor);
        Price processedPrice = decoratedPriceProcessor.process(unprocessedPrice);
        System.out.println(processedPrice.getPrice());


        PriceProcessor decoratedPriceProcessor2 = basicPriceProcessor
                .andThen(discountPriceProcessor)
                .andThen(price -> new Price(price.getPrice() + ", then apply another procedure"));
        Price processedPrice2 = decoratedPriceProcessor2.process(unprocessedPrice);
        System.out.println(processedPrice2.getPrice());
    }
}
