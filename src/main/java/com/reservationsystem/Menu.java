package com.reservationsystem;

import com.reservationsystem.config.CheckList;
import com.reservationsystem.direction.Filter;
import com.reservationsystem.dto.Customer;
import com.reservationsystem.dto.Employee;
import com.reservationsystem.dto.Reservation;
import com.reservationsystem.dto.Room;
import com.reservationsystem.exception.DuplicateRoomNumber;
import com.reservationsystem.exception.WrongCustomerId;
import com.reservationsystem.exception.WrongNumberException;
import com.reservationsystem.service.CustomerService;
import com.reservationsystem.service.EmployeeService;
import com.reservationsystem.service.ReservationService;
import com.reservationsystem.service.RoomService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Menu {

    private boolean exitApplication = true;
    private Scanner keyboard = new Scanner(System.in);
    private CustomerService customerService = new CustomerService();
    private EmployeeService employeeService = new EmployeeService();
    private RoomService roomService = new RoomService();
    private ReservationService reservationService = new ReservationService();
    private int roomNumber;
    private String fullName;
    private String[] splitFullName;
    private String role;
    private String personalSkill;
    private Boolean idIsOnList;
    private int roomSize;
    private String equipment;
    private BigDecimal price;
    private int customerId;
    private LocalDate startReservationDate;
    private LocalDate endReservationDate;
    private Room room;
    private BigDecimal sum;
    private BigDecimal deposit;
    private boolean isFullPaid;
    private int employerId;
    private Room byRoomNumber;
    private BigDecimal totalSum;
    private LocalDate startDate;
    private LocalDate endDate;

    public void menu() {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("RESERVATION SYSTEM");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Wybierz jedną z opcji");
        System.out.println("(0) Wyjście z aplikacji");
        System.out.println("KLIENCI");
        System.out.println("(1) Dodaj dane klienta");
        System.out.println("(2) Pokaż wszystkich klientów");
        System.out.println("(3) Zaktualizuj dane klienta");
        System.out.println("(4) Usuń klienta");
        System.out.println("...........................");
        System.out.println("PRACOWNICY");
        System.out.println("(5) Dodaj dane pracownika");
        System.out.println("(6) Pokaż wszystkich pracowników");
        System.out.println("(7) Zaktualizuj dane pracownika");
        System.out.println("(8) Usuń pracownika");
        System.out.println("...........................");
        System.out.println("POKOJE");
        System.out.println("(9) Dodaj pokój");
        System.out.println("(10) Pokaż dane o pokojach");
        System.out.println("(11) Zaktualizuj dane o pokoju");
        System.out.println("(12) Usuń pokój");
        System.out.println("...........................");
        System.out.println("REZERWACJE");
        System.out.println("(13) Dodaj rezerwację");
        System.out.println("(14) Pokaż dane o rezerwacji");
        System.out.println("(15) Zaktualizuj dane o rezerwacji");
        System.out.println("(16) Usuń rezerwację");

        int index = keyboard.nextInt();
        switch (index) {
            case 0:
                exitApplication = false;
                break;
            case 1:
                System.out.println("DODAWANIE KLIENTA:");
                keyboard.nextLine();
                System.out.println("Dodaj imię i nazwisko");
                fullName = keyboard.nextLine();
                splitFullName = validateFullName(fullName);
                System.out.println("Dodaj numer PESEL");
                String pesel = keyboard.nextLine();
                customerService.create(new Customer(splitFullName[0], splitFullName[1], pesel));
                break;
            case 2:
                System.out.println("LISTA KLIENTÓW:");
                checkListNotNull(CheckList.builder().customer(new Customer()).build(), Filter.SHOW);
                break;
            case 3:
                System.out.println("AKTUALIZACJA DANYCH KLIENTA:");
                checkListNotNull(CheckList.builder().customer(new Customer()).build(), Filter.UPDATE);
                //TODO jesli podasz cos innego niz int to wywala bledem i cofa do menu
                customerId = keyboard.nextInt();
                if (customerService.checkId(customerId)) {
                    System.out.println("Podaj nowe imię i nazwisko klienta.");
                    keyboard.nextLine();
                    String updateFullName = keyboard.nextLine();
                    String[] splitUpdateFullName = validateFullName(updateFullName);
                    System.out.println("Podaj nowy numer PESEL");
                    String updatePesel = keyboard.nextLine();
                    customerService.update(new Customer(customerId, splitUpdateFullName[0], splitUpdateFullName[1], updatePesel));
                } else {
                    System.out.println("Brak klienta o podanym id");
                }
                break;
            case 4:
                do {
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("USUNIĘCIE DANYCH KLIENTA.");
                    checkListNotNull(CheckList.builder().customer(new Customer()).build(), Filter.DELETE);
                    customerId = keyboard.nextInt();
                    idIsOnList = customerService.delete(customerId);
                } while (!idIsOnList);
                break;
            case 5:
                System.out.println("DODAWANIE PRACOWNIKA:");
                keyboard.nextLine();
                System.out.println("Dodaj imię i nazwisko");
                fullName = keyboard.nextLine();
                splitFullName = validateFullName(fullName);
                System.out.println("Dodaj stanowisko");
                role = keyboard.nextLine();
                System.out.println("Dodaj umiejętności pracownika");
                personalSkill = keyboard.nextLine();
                employeeService.create(new Employee(splitFullName[0], splitFullName[1], role, personalSkill));
                break;
            case 6:
                System.out.println("LISTA PRACOWNIKÓW:");
                checkListNotNull(CheckList.builder().employee(new Employee()).build(), Filter.SHOW);
                break;
            case 7:
                System.out.println("AKTUALIZACJA DANYCH PRACOWNIKA:");
                checkListNotNull(CheckList.builder().employee(new Employee()).build(), Filter.UPDATE);
                //TODO jesli podasz cos innego niz int to wywala bledem i cofa do menu
                employerId = keyboard.nextInt();
                if (employeeService.checkId(employerId)) {
                    System.out.println("Podaj nowe imię i nazwisko pracownika.");
                    keyboard.nextLine();
                    String updateFullName = keyboard.nextLine();
                    String[] splitUpdateFullName = validateFullName(updateFullName);
                    System.out.println("Podaj nowe stanowisko pracownika");
                    role = keyboard.nextLine();
                    System.out.println("Podaj nowe umiejętności pracownika");
                    personalSkill = keyboard.nextLine();
                    employeeService.update(new Employee(employerId, splitUpdateFullName[0], splitUpdateFullName[1], role, personalSkill));
                } else {
                    System.out.println("Brak pracownika o podanym id");
                }
                break;
            case 8:
                do {
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("USUNIĘCIE DANYCH PRACOWNIKA.");
                    checkListNotNull(CheckList.builder().employee(new Employee()).build(), Filter.DELETE);
                    employerId = keyboard.nextInt();
                    idIsOnList = employeeService.delete(employerId);
                } while (idIsOnList == false);
                break;
            case 9:
                System.out.println("DODAWANIE POKOJU:");
                keyboard.nextLine();
                boolean isDuplicate = true;
                do {
                    try{
                        System.out.println("Dodaj numer pokoju");
                        roomNumber = keyboard.nextInt();
                        roomService.validateRoomNumber(roomNumber);
                        isDuplicate = false;
                    } catch(DuplicateRoomNumber d) {
                        System.err.println(d.getLocalizedMessage());
                    }
                } while (isDuplicate);
                System.out.println("Dodaj rozmiar pokoju");
                roomSize = keyboard.nextInt();
                keyboard.nextLine();
                System.out.println("Dodaj sprzęt w pokoju");
                equipment = keyboard.nextLine();
                System.out.println("Podaj cenę wynajęcia pokoju");
                price = keyboard.nextBigDecimal();
                roomService.create(Room.builder()
                        .roomNumber(roomNumber)
                        .roomSize(roomSize)
                        .equipment(equipment)
                        .price(price)
                        .build());
                break;
            case 10:
                System.out.println("POKOJE:");
                checkListNotNull(CheckList.builder().room(new Room()).build(), Filter.SHOW);
                break;
            case 11:
                System.out.println("AKTUALIZACJA DANYCH O POKOJACH:");
                System.out.println("LISTA POKOI");
                checkListNotNull(CheckList.builder().room(new Room()).build(), Filter.UPDATE);
                roomNumber = keyboard.nextInt();
                room = roomService.getByRoomNumber(roomNumber);
                if (room != null) {
                    System.out.println("Podaj nowy numer pokoju.");
                    roomNumber = keyboard.nextInt();
                    System.out.println("Podaj nowy rozmiar pokoju.");
                    roomSize = keyboard.nextInt();
                    keyboard.nextLine();
                    System.out.println("Podaj nowe wyposażenie pokoju");
                    equipment = keyboard.nextLine();
                    System.out.println("Podaj nową cenę pokoju");
                    price = keyboard.nextBigDecimal();
                    roomService.update(new Room(room.getId(), roomNumber, roomSize, equipment, price));
                } else {
                    System.out.println("Brak pokoju o podanym id");
                }
                break;
            case 12:
                do {
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("USUNIĘCIE POKOJU.");
                    checkListNotNull(CheckList.builder().room(new Room()).build(), Filter.DELETE);
                    roomNumber = keyboard.nextInt();
                    idIsOnList = roomService.delete(roomNumber);
                } while (idIsOnList == false);
                break;
            case 13:
                System.out.println("DODAWANIE REZERWACJI");
                System.out.println("..........................");
                reservationService.create(addReservation());
                break;
            case 14:
                System.out.println("LISTA REZERWACJI");
                checkListNotNull(CheckList.builder().reservation(new Reservation()).build(), Filter.SHOW);
                break;
            case 15:
                System.out.println("AKTUALIZACJA REZERWACJI");
                System.out.println("LISTA REZERWACJI");
                checkListNotNull(CheckList.builder().reservation(new Reservation()).build(), Filter.SHOW);
                System.out.println("Podaj numer rezerwacji, którą chcesz aktualizować.");
                int reservationId = keyboard.nextInt();
                if(reservationService.getReservation(reservationId) != null) {
                    Reservation reservation = addReservation();
                    reservation.setId(reservationId);
                    reservationService.update(reservation);
                }
                System.out.println("Błędny id rezerwacji.");
                break;
            case 16:
                System.out.println("USUWANIE REZERWACJI");


            default:
                System.out.println("Brak takiej opcji.");
        }
    }

    private void checkListNotNull(CheckList checkList, Filter filter) {
        if (checkList.getCustomer() != null) {
            if (customerService.findAll() != null) {
                if (!customerService.findAll().isEmpty()) {
                    if (Filter.DELETE.equals(filter)) {
                        System.out.println("Wybierz klienta, którego dane chcesz usunąć.");
                    }
                    if (Filter.UPDATE.equals(filter)) {
                        System.out.println("Wybierz klienta, którego dane chcesz zmienić.");
                    }
                    customerService.findAll().forEach(customer -> {
                        System.out.println(customer.toString());
                    });
                } else {
                    System.out.println("Brak listy klientów.");
                    showMenu();
                }
            } else {
                System.out.println("Brak listy klientów.");
                showMenu();
            }
        } else if (checkList.getEmployee() != null) {
            if (employeeService.findAll() != null) {
                if (!employeeService.findAll().isEmpty()) {
                    if (Filter.DELETE.equals(filter)) {
                        System.out.println("Wybierz pracownika, którego dane chcesz usunąć.");
                    }
                    if (Filter.UPDATE.equals(filter)) {
                        System.out.println("Wybierz pracownika, którego dane chcesz zmienić.");
                    }
                    employeeService.findAll().forEach(customer -> {
                        System.out.println(customer.toString());
                    });
                } else {
                    System.out.println("Brak listy pracowników.");
                    showMenu();
                }
            } else {
                System.out.println("Brak listy pracowników.");
                showMenu();
            }
        } else if (checkList.getRoom() != null) {
            if (roomService.findAll() != null) {
                if (!roomService.findAll().isEmpty()) {
                    if (Filter.DELETE.equals(filter)) {
                        System.out.println("Wybierz pokój, który chcesz usunąć.");
                    }
                    if (Filter.UPDATE.equals(filter)) {
                        System.out.println("Wybierz pokój, który chcesz zmienić.");
                    }
                    roomService.findAll().forEach(room -> {
                        System.out.println(room.toString());
                    });
                } else {
                    System.out.println("Brak listy pokoi.");
                    showMenu();
                }
            } else {
                System.out.println("Brak listy pokoi.");
                showMenu();
            }
        } else if (checkList.getReservation() != null) {
            if (reservationService.findAll() != null) {
                if (!reservationService.findAll().isEmpty()) {
                    if (Filter.DELETE.equals(filter)) {
                        System.out.println("Wybierz rezerwację, którą chcesz usunąć.");
                    }
                    if (Filter.UPDATE.equals(filter)) {
                        System.out.println("Wybierz rezerwację, którą chcesz zmienić.");
                    }
                    reservationService.findAll().forEach(reservation -> {
                        System.out.println(reservation.toString());
                    });
                } else {
                    System.out.println("Brak listy rezerwacji.");
                    showMenu();
                }
            } else {
                System.out.println("Brak listy rezerwacji.");
                showMenu();
            }
        }
    }


    private String[] validateFullName(String fullName) {
        String[] splitName = new String[2];
        try {
            String[] split = fullName.split(" ");
            splitName[0] = split[0];
            splitName[1] = split[1];
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            System.err.println("Podałeś błędnie imię i nazwisko.");
            menu();
        }
        return splitName;
    }

    public LocalDate getDate(){
        int day;
        int month;
        int year;
        System.out.println("Podaj dzień");
        day = keyboard.nextInt();
        System.out.println("Podaj miesiąc");
        month = keyboard.nextInt();
        System.out.println("Podaj rok");
        year = keyboard.nextInt();
        return LocalDate.of(year, month, day);
    }

    public boolean checkPayment() {
        String isFullPaid = keyboard.nextLine().trim().toUpperCase();
            if(isFullPaid.equals("TAK")) {
                return true;
            } else if (isFullPaid.equals("NIE")) {
                return false;
            } else {
                System.out.println("Niepoprawna odpowiedź. Wpisz TAK lub NIE.");
                return checkPayment();
            }
    }

    public Reservation addReservation() {
        System.out.println("LISTA KLIENTÓW");
        checkListNotNull(CheckList.builder().customer(new Customer()).build(), Filter.SHOW);
        int customer = 0;
        boolean customerId = true;
        do {
            try {
                System.out.println("Podaj numer klienta.");
                customer = keyboard.nextInt();
                customerService.getCustomer(customer);
                customerId = false;
            } catch (WrongCustomerId w) {
                System.err.println(w.getLocalizedMessage());
            }
        } while(customerId);
        System.out.println("Podaj datę początku rezerwacji.");
        startDate = getDate();
        System.out.println("Podaj datę zakończenia rezerwacji.");
        endDate = getDate();
        System.out.println("Podaj numer pokoju, którego będzie dotyczyć rezerwacja.");
        System.out.println("..................");
        boolean roomId = true;
        Room byRoomNumber = new Room();
        do {
            try {
                System.out.println("LISTA POKOI.");
                checkListNotNull(CheckList.builder().room(new Room()).build(), Filter.SHOW);
                roomNumber = keyboard.nextInt();
                byRoomNumber = roomService.getByRoomNumber(roomNumber);
                roomId = false;
            } catch (WrongNumberException w) {
                System.err.println(w.getLocalizedMessage());
            }
        } while (roomId);
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        price = byRoomNumber.getPrice();
        BigDecimal totalSum = price.multiply(BigDecimal.valueOf(days));
        System.out.println("Całkowita suma: " + totalSum + "PLN");
        System.out.println("Podaj kwotę depozytu jaką klient wpłacił.");
        BigDecimal payment = keyboard.nextBigDecimal();
        keyboard.nextLine();
        System.out.println("Czy klient zapłacił całą kwotę? TAK/NIE");
        boolean result = checkPayment();
        System.out.println("Lista pracowników.");
        checkListNotNull(CheckList.builder().employee(new Employee()).build(), Filter.SHOW);
        System.out.println("Podaj numer pracownika");
        int employer = keyboard.nextInt();
        return Reservation.builder()
                .customerId(customer)
                .startReservationDate(startDate)
                .endReservationDate(endDate)
                .room(byRoomNumber)
                .sum(totalSum)
                .deposit(payment)
                .isFullPaid(result)
                .employerId(employer).build();
    }


    public void showMenu() {
        while (exitApplication) {
            try {
                menu();
            } catch (IllegalArgumentException e) {
                e.getMessage();
            }
        }
    }
}
