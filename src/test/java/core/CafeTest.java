package core;

import org.junit.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CafeTest {

    private static final int ESPRESSO_REQUIRED_MILK = CoffeeType.Espresso.getRequiredMilk();
    private static final int ESPRESSO_REQUIRED_BEANS = CoffeeType.Espresso.getRequiredBeans();
    private static final int LATTE_REQUIRED_BEANS = CoffeeType.Latte.getRequiredBeans();
    private static final int LATTE_REQUIRED_MILK = CoffeeType.Latte.getRequiredMilk();
    private static final int NO_BEANS = 0;

    private Cafe cafe;

    @Before
    public void before(){
        cafe = new Cafe();
    }

    @Test
    public void canBrewEspresso(){

        //Given
        withBeans(ESPRESSO_REQUIRED_BEANS);

        //When
        Coffee coffee = cafe.brew(CoffeeType.Espresso);

        //Then
        assertThat(coffee, hasProperty("beans", equalTo(ESPRESSO_REQUIRED_BEANS)));
        assertEquals("Wrong Amount of Milk", ESPRESSO_REQUIRED_MILK, coffee.getMilk());
        assertEquals("Wrong Amount of Beans", ESPRESSO_REQUIRED_BEANS, coffee.getBeans());
    }

    @Test
    public void canBrewLatte(){

        withBeans(LATTE_REQUIRED_BEANS);
        cafe.restockMilk(LATTE_REQUIRED_MILK);

        Coffee coffee = cafe.brew(CoffeeType.Latte);

        assertEquals(CoffeeType.Latte, coffee.getType());
        assertEquals(LATTE_REQUIRED_BEANS, coffee.getBeans());
        assertEquals(LATTE_REQUIRED_MILK, coffee.getMilk());
    }

    @Test
    public void brewingEspressoConsumesBeans(){

        //Given
        withBeans(LATTE_REQUIRED_BEANS);

        //When
        Coffee coffee = cafe.brew(CoffeeType.Espresso);

        //Then
        assertEquals(NO_BEANS, cafe.getBeansInStock());
    }

    @Test(expected = IllegalStateException.class)
    public void lattesRequiresMilk(){

        withBeans(LATTE_REQUIRED_BEANS);

        cafe.brew(CoffeeType.Latte);
    }

    private void withBeans(int numBeans) {
        cafe.restockBeans(numBeans);
    }
}