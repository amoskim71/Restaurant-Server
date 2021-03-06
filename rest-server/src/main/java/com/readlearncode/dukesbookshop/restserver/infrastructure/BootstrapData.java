package com.readlearncode.dukesbookshop.restserver.infrastructure;

import com.readlearncode.dukesbookshop.restserver.domain.*;
import com.readlearncode.dukesbookshop.restserver.infrastructure.DAOInterface.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author navid
 * @version 1.0
 * Deleted since the hibernate and database connected to the project!
 */

public class BootstrapData {

    private static final String API_URL = "http://localhost:8081/rest-server";
    private static final String IMAGE_LOCATION = "/images/covers/";

    @EJB
    private ReservationRepository resRepo;

    @EJB
    private TableRepository tableRepo;

    @EJB
    private CustomerRepository customerRepo;

    @EJB
    private MenuItemRepository myRestaurantMenu;

    @EJB
    private OrderRepository orderRepo;

    @PostConstruct
    public void populateRepositories() {
        try {

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            Optional<Customer> navid = customerRepo.createNewProfile("Navid Alipoor", "9383709786");
            Optional<Customer> john = customerRepo.createNewProfile("John Doe", "9383719786");
            Optional<Customer> batman = customerRepo.createNewProfile("Bruce Wayne", "9383709786");

            Table tb1 = tableRepo.createTable(4).get();
            Table tb2 = tableRepo.createTable(6).get();
            Table tb3 = tableRepo.createTable(12).get();

            // most fanciest Iranian menu :D
            myRestaurantMenu.addToFoods(new MenuItem("Mast-o-Khiar", 8.00f));
            myRestaurantMenu.addToFoods(new MenuItem("Abgoosht", 15.00f));
            myRestaurantMenu.addToFoods(new MenuItem("Ghorme Sabzi", 15.00f));
            myRestaurantMenu.addToFoods(new MenuItem("Fesenjoon", 20.00f));

            myRestaurantMenu.addToDrinks(new MenuItem("NooshabA siah", 2.00f));
            myRestaurantMenu.addToDrinks(new MenuItem("Doogh Ab-Ali", 2.00f));

            myRestaurantMenu.addToDesserts(new MenuItem("Shole-zard", 3.00f));
            myRestaurantMenu.addToDesserts(new MenuItem("Faloode-shirazi", 5.00f));
            myRestaurantMenu.addToDesserts(new MenuItem("Bastani Sonnati", 4.00f));


            //Reservation reservation = resRepo.saveReserve(tb1, navid, new TimeSpan("1400", "1600"), LocalDate.now());

            HashMap<String, Integer> myOrderItems = new HashMap<>();
            myOrderItems.put("Abgoosht", 3);
            myOrderItems.put("Shole-zard", 3);
            myOrderItems.put("Doogh Ab-Ali", 3);
            myOrderItems.put("Faloode-shirazi",4);


            //Order myOrder = new Order(1, reservation, myOrderItems);

            /*
                following line saves an order its also included in the postman test...
                so if you want to add it you can un comment the line below...
                or just call the api from postman
            */
            //orderRepo.saveOrderForReservation(reservation, myOrder);

        } catch (Exception e) {
            System.out.println("Exception thrown while bootstrapping. " + e);
        }

    }


}