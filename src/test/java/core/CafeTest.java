package core;

import org.junit.Test;

import static org.junit.Assert.*;

public class CafeTest {

    @Test
    public void canBrewEspresso(){

        //Given
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);

        //When
        Coffee coffee = cafe.brew(CoffeeType.Espresso);

        //Then
        assertEquals(CoffeeType.Espresso, coffee.getType());
        assertEquals(0, coffee.getMilk());
        assertEquals(7, coffee.getBeans());
    }

    @Test
    public void canBrewLatte(){

        Cafe cafe = new Cafe();
        cafe.restockBeans(10);
        cafe.restockMilk(300);

        Coffee coffee = cafe.brew(CoffeeType.Latte);

        assertEquals(CoffeeType.Latte, coffee.getType());
        assertEquals(7, coffee.getBeans());
        assertEquals(227, coffee.getMilk());
    }

    @Test
    public void brewingEspressoConsumesBeans(){

        //Given
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);

        //When
        Coffee coffee = cafe.brew(CoffeeType.Espresso);

        //Then
        assertEquals(0, cafe.getBeansInStock());
    }

    @Test(expected = IllegalStateException.class)
    public void lattesRequiresMilk(){

        Cafe cafe = new Cafe();
        cafe.restockBeans(7);

        cafe.brew(CoffeeType.Latte);
    }
}