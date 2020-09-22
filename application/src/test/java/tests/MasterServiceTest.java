package tests;

import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.service.interfaces.IMasterService;
import config.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class MasterServiceTest {

    @Autowired
    private IMasterService masterService;
    @Autowired
    private TestData testData;

    @BeforeAll
    public static void startMasterServiceTests() {
        log.info("Starting master service tests");
    }

    @Test
    public void validateNewMasterAdding() {
        log.info("Validating new master adding");
        assertEquals(masterService.addMaster(1, "Ivan", 1), 1);
    }

    @Test
    public void validateMasterRemoving() {
        log.info("Validating master removing");
        assertEquals(masterService.removeMaster("Ivan"), 1);
        assertEquals(masterService.removeMaster("Arsen"), 0);
    }

    @Test
    public void validateMasterUpdating() {
        log.info("Validating master updating");
        Master master = testData.getMastersList().get(0);
        master.setCategory(6);
        assertEquals(masterService.updateMaster(master), 1);
    }

    @Test
    public void validateGettingAllMasters() {
        log.info("Validating getting all masters");
        assertEquals(masterService.getAllMasters(), testData.getMastersList());
    }

    @Test
    public void validateGettingAllMastersSorted() {
        log.info("Validating getting all masters sorted");
        List <Master> allMastersSorted = new ArrayList<>(testData.getMastersList());
        Collections.sort(allMastersSorted, Comparator.comparing(Master::getName));
        assertEquals(masterService.getAllMastersSorted("byName"), allMastersSorted);

        allMastersSorted = new ArrayList<>(testData.getMastersList());
        Collections.sort(allMastersSorted, (master, t1) -> {
            if (master.isBusy() == t1.isBusy()) return 0;
            else
            if (master.isBusy()) return 1;
            else return -1;
        });
        assertEquals(masterService.getAllMastersSorted("byBusy"), allMastersSorted);
        assertEquals(masterService.getAllMastersSorted("wrongSortMethod"), testData.getMastersList());
    }

    @Test
    public void validateGettingAllFreeMasters() {
        log.info("Validating getting all free masters");
        assertEquals(masterService.getAllFreeMasters(), testData.getAllFreeMasters());
    }

    @Test
    public void validateGettingCurrentOrder() {
        log.info("Validating current order getting");
        assertEquals(masterService.getCurrentOrder("Evgeniy"), testData.getOrdersList().get(0));
        assertNotEquals(masterService.getCurrentOrder("Ivan"), testData.getOrdersList().get(0));
        assertEquals(masterService.getCurrentOrder("UnknownMaster"), null);
    }

    @Test
    public void validateFindingMasterByName() {
        log.info("Validating finding master by name");
        assertEquals(masterService.findMasterByName("Ivan"), testData.getMastersList().get(2));
        assertNotEquals(masterService.findMasterByName("Evgeniy"), testData.getMastersList().get(2));
        assertEquals(masterService.findMasterByName("UnknownMaster"), null);
    }

    @Test
    public void validateFindingMasterById() {
        log.info("Validating finding master by id");
        assertEquals(masterService.findMasterById(1), testData.getMastersList().get(0));
        assertEquals(masterService.findMasterById(10), null);
    }

    @AfterAll
    public static void endMasterServiceTests() {
        log.info("Master service tests are completed");
    }

}
