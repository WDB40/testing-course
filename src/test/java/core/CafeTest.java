package core;

import org.junit.Test;

import static org.junit.Assert.*;

public class CafeTest {

    @Test
    public void canBrewEspresso(){

        Cafe cafe = new Cafe();
        cafe.restockBeans(7);
        Coffee coffee = cafe.brew(CoffeeType.Espresso);

        assertEquals(CoffeeType.Espresso, coffee.getType());
        assertEquals(0, coffee.getMilk());
        assertEquals(7, coffee.getBeans());
    }

}