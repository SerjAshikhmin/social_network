package tests;

import com.senla.courses.autoservice.service.GarageService;
import com.senla.courses.autoservice.service.MasterService;
import config.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class GarageServiceTest {

    @Autowired
    private GarageService garageService;
    @Autowired
    private MasterService masterService;
    @Autowired
    private TestData testData;

    @BeforeAll
    public static void startMasterServiceTests() {
        log.info("Starting garage service tests");
    }

    @Test
    public void validateNewGarageAdding() {
        log.info("Validating new garage adding");
        assertEquals(garageService.addGarage(1, "Orel-Moskovskaya-22"), 1);
    }

    @Test
    public void validateGarageRemoving() {
        log.info("Validating garage removing");
        assertEquals(garageService.removeGarage(1), 1);
        assertEquals(garageService.removeGarage(5), 0);
    }

    @Test
    public void validateGettingAllGarages() {
        log.info("Validating getting all garages");
        assertEquals(garageService.getAllGarages(), testData.getGarageList());
    }

    @Test
    public void validateNewGaragePlaceAdding() {
        log.info("Validating new garage place adding");
        assertEquals(garageService.addGaragePlace(1, 1, "Car lift", 8), 1);
    }

    @Test
    public void validateGaragePlaceRemoving() {
        log.info("Validating garage place removing");
        assertEquals(garageService.removeGaragePlace(1, 1), 1);
        assertEquals(garageService.removeGaragePlace(1, 5), 0);
    }

    @Test
    public void validateGettingAllFreePlaces() {
        log.info("Validating getting all free places");
        assertEquals(garageService.getAllFreePlaces(), testData.getAllFreePlaces());
    }

    @Test
    public void validateGettingFreePlacesCountInFuture() {
        log.info("Validating of getting free places count in future");
        assertEquals(garageService.getFreePlacesCountInFuture(), Math.min(testData.getAllFreePlaces().size(), testData.getAllFreeMasters().size()));
    }

    @Test
    public void validateFindingGaragePlaceById() {
        log.info("Validating finding garage place by id");
        assertEquals(garageService.findGaragePlaceById(1, 1), testData.getGarageList().get(0).getGaragePlaces().get(0));
        assertEquals(garageService.findGaragePlaceById(10, 1), null);
    }

    @Test
    public void validateFindingGarageById() {
        log.info("Validating finding garage by id");
        assertEquals(garageService.findGarageById(1), testData.getGarageList().get(0));
        assertEquals(garageService.findGarageById(10), null);
    }

    @AfterAll
    public static void endGarageServiceTests() {
        log.info("Garage service tests are completed");
    }

}
