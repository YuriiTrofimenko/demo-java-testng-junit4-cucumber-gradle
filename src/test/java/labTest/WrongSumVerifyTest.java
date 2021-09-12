package labTest;

import global.Facade;
import model.RozetkaFilter;
import model.RozetkaFilters;
import model.ValueWrapper;
import org.testng.Assert;
import org.testng.annotations.*;
import util.XMLtoObject;

import java.util.List;

public class WrongSumVerifyTest {

        private static final int WRONG_EXPECTED_ORDER_PRICE_TOTAL_MAX_BOUND = 100;

        private Facade domManipulatorFacade;

        @BeforeClass
        public void appSetup () {
            domManipulatorFacade = new Facade();
        }

        @BeforeMethod
        public void testsSetUp() {
            domManipulatorFacade.open("https://rozetka.com.ua/");
        }

        @DataProvider(parallel = true)
        public Object[][] products() {
            XMLtoObject xmLtoObject = new XMLtoObject();
            RozetkaFilters rozetkaFilters = xmLtoObject.convert();
            List<RozetkaFilter> rozetkaFilterList = rozetkaFilters.getRozetkaFilters();
            int rowAmount = rozetkaFilterList.size();
            int columnAmount = 1;
            Object[][] rozetkaFilterArray = new Object[rowAmount][columnAmount];
            for (int i = 0; i < rozetkaFilterList.size(); i++) {
                rozetkaFilterArray[i][0] = rozetkaFilterList.get(i);
            }
            return rozetkaFilterArray;
        }

        @Test(dataProvider = "products")
        public void givenFilter_whenTheMostExpensiveProductAddedToCart_thenTotalPriceLessThanBound (RozetkaFilter rozetkaFilter) throws InterruptedException {
            ValueWrapper<String> productTitleFromProduct = new ValueWrapper<>();
            ValueWrapper<String> productTitleFromCart = new ValueWrapper<>();
            ValueWrapper<Integer> cartTotalPrice = new ValueWrapper<>();
            domManipulatorFacade.filterProductsByCategory(rozetkaFilter.getProductGroup())
                .filterProductsByBrand(rozetkaFilter.getBrand())
                .sortProductsFromExpensive()
                .chooseFirstProduct()
                .getProductTitleFromProduct(productTitleFromProduct)
                .addProductToCart()
                .getProductTitleFromCart(productTitleFromCart)
                .getCartTotalPrice(cartTotalPrice);
            Assert.assertEquals(productTitleFromCart.value, productTitleFromProduct.value);
            // System.out.printf("%s < %s", cartTotalPrice.value, WRONG_EXPECTED_ORDER_PRICE_TOTAL_MAX_BOUND);
            Assert.assertTrue(cartTotalPrice.value < WRONG_EXPECTED_ORDER_PRICE_TOTAL_MAX_BOUND);
        }

    /* @Test(dataProvider = "products")
    public void givenFilter_whenProductsPageSearch_thenSuccess (RozetkaFilter rozetkaFilter) throws InterruptedException {
        domManipulatorFacade.filterProductsByCategory(rozetkaFilter.getProductGroup())
            .filterProductsByCategory(rozetkaFilter.getProductGroup());
    } */

        @AfterMethod
        public void tearDown() {
            domManipulatorFacade.close();
        }
    }

