package config;

import com.senla.courses.autoservice.dao.GarageDao;
import com.senla.courses.autoservice.dao.GaragePlaceDao;
import com.senla.courses.autoservice.dao.MasterDao;
import com.senla.courses.autoservice.dao.OrderDao;
import com.senla.courses.autoservice.dao.jpadao.DbJpaConnector;
import com.senla.courses.autoservice.exceptions.MasterNotFoundException;
import com.senla.courses.autoservice.exceptions.OrderNotFoundException;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.service.GarageService;
import com.senla.courses.autoservice.service.MasterService;
import com.senla.courses.autoservice.service.OrderService;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import tests.TestData;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;


@Configuration
@PropertySource("classpath:config.properties")
public class TestConfig {

    @Autowired
    private TestData testData;

    @Bean
    public MasterService masterService() {
        return new MasterService();
    }

    @Bean
    public GarageService garageService() {
        return new GarageService();
    }

    @Bean
    public OrderService orderService() {
        return new OrderService();
    }

    @Bean
    public TestData testData() {
        return new TestData();
    }

    @Bean
    public MasterDao masterDao() throws MasterNotFoundException {
        MasterDao masterDaoMock = mock(MasterDao.class);
        when(masterDaoMock.addMaster(any(Master.class))).thenReturn(1);
        when(masterDaoMock.removeMaster(any(Master.class))).thenReturn(1);
        when(masterDaoMock.updateMaster(any(Master.class))).thenReturn(1);
        when(masterDaoMock.getMasterById(1)).thenReturn(testData.getMastersList().get(0));
        when(masterDaoMock.getAllMasters()).thenReturn(testData.getMastersList());
        when(masterDaoMock.getCurrentOrder(testData.getMastersList().get(0))).thenReturn(testData.getMastersList().get(0).getOrder());
        return masterDaoMock;
    }

    @Bean
    public GarageDao garageDao() {
        GarageDao garageDaoMock = mock(GarageDao.class);
        when(garageDaoMock.addGarage(any(Garage.class))).thenReturn(1);
        when(garageDaoMock.removeGarage(any(Garage.class))).thenReturn(1);
        when(garageDaoMock.updateGarage(any(Garage.class))).thenReturn(1);
        when(garageDaoMock.getGarageById(1)).thenReturn(testData.getGarageList().get(0));
        when(garageDaoMock.findAll()).thenReturn(testData.getGarageList());
        when(garageDaoMock.getAllGarages()).thenReturn(testData.getGarageList());
        return garageDaoMock;
    }

    @Bean
    public GaragePlaceDao garagePlaceDao() {
        GaragePlaceDao garagePlaceDaoMock = mock(GaragePlaceDao.class);
        when(garagePlaceDaoMock.addGaragePlace(any(GaragePlace.class))).thenReturn(1);
        when(garagePlaceDaoMock.removeGaragePlace(any(GaragePlace.class))).thenReturn(1);
        when(garagePlaceDaoMock.updateGaragePlace(any(GaragePlace.class))).thenReturn(1);
        when(garagePlaceDaoMock.getGaragePlaceById(1, 1)).thenReturn(testData.getGarageList().get(0).getGaragePlaces().get(0));
        return garagePlaceDaoMock;
    }

    @Bean
    public OrderDao orderDao() throws OrderNotFoundException {
        OrderDao orderDaoMock = mock(OrderDao.class);
        when(orderDaoMock.addOrder(any(Order.class))).thenReturn(1);
        when(orderDaoMock.removeOrder(any(Order.class))).thenReturn(1);
        when(orderDaoMock.updateOrder(any(Order.class))).thenReturn(1);
        when(orderDaoMock.getOrderById(1)).thenReturn(testData.getOrdersList().get(0));
        when(orderDaoMock.getAllOrders()).thenReturn(testData.getOrdersList());
        when(orderDaoMock.findAll()).thenReturn(testData.getOrdersList());
        when(orderDaoMock.getMastersByOrder(testData.getOrdersList().get(0))).thenReturn(testData.getOrdersList().get(0).getMasters());
        return orderDaoMock;
    }

    @Bean
    public DbJpaConnector dbJpaConnector() {
        DbJpaConnector dbJpaConnectorMock = mock(DbJpaConnector.class);
        when(dbJpaConnectorMock.getTransaction()).thenReturn(new EntityTransaction() {
            @Override
            public void begin() {
            }

            @Override
            public void commit() {
            }

            @Override
            public void rollback() {
            }

            @Override
            public void setRollbackOnly() {
            }

            @Override
            public boolean getRollbackOnly() {
                return false;
            }

            @Override
            public boolean isActive() {
                return false;
            }
        });

        return dbJpaConnectorMock;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return mock(EntityManagerFactory.class);
    }
}
