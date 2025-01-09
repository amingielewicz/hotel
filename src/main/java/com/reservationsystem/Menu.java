package com.reservationsystem;

import com.reservationsystem.config.CheckList;
import com.reservationsystem.direction.Filter;
import com.reservationsystem.dto.Customer;
import com.reservationsystem.dto.Employee;
import com.reservationsystem.dto.Room;
import com.reservationsystem.service.CustomerService;
import com.reservationsystem.service.EmployeeService;
import com.reservationsystem.service.RoomService;

import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {

    private boolean exitApplication = true;
    private Scanner keyboard = new Scanner(System.in);
    private CustomerService customerService = new CustomerService();
    private EmployeeService employeeServic = new EmployeeService();
    private RoomService roomService = new RoomService();
    private int id;
    private String fullName;
    private String[] splitFullName;
    private String role;
    private String personalSkill;
    private Boolean idIsOnList;
    private int roomNumber;
    private int roomSize;
    private String equipment;
    private BigDecimal price;

    public void menu() {
        System.out.println("RESERVATION SYSTEM");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Wybierz jedną z opcji");
        System.out.println("(0) Wyjście z aplikacji");
        System.out.println("KLIENCI");
        System.out.println("(1) Dodaj dane klienta");
        System.out.println("(2) Pokaż wszystkich klientów");
        System.out.println("(3) Zaktualizuj dane klienta");
        System.out.println("(4) Usuń klienta");
        System.out.println("PRACOWNICY");
        System.out.println("(5) Dodaj dane pracownika");
        System.out.println("(6) Pokaż wszystkich pracowników");
        System.out.println("(7) Zaktualizuj dane pracownika");
        System.out.println("(8) Usuń pracownika");
        System.out.println("POKOJE");
        System.out.println("(9) Dodaj pokój");
        System.out.println("(10) Pokaż dane o pokojach");
        System.out.println("(11) Zaktualizuj dane o pokoju");
        System.out.println("(12) Usuń pokój");


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
                id = keyboard.nextInt();
                if (customerService.checkId(id)) {
                    System.out.println("Podaj nowe imię i nazwisko klienta.");
                    keyboard.nextLine();
                    String updateFullName = keyboard.nextLine();
                    String[] splitUpdateFullName = validateFullName(updateFullName);
                    System.out.println("Podaj nowy numer PESEL");
                    String updatePesel = keyboard.nextLine();
                    customerService.update(new Customer(id, splitUpdateFullName[0], splitUpdateFullName[1], updatePesel));
                } else {
                    System.out.println("Brak klienta o podanym id");
                }
                break;
            case 4:
                do {
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("USUNIĘCIE DANYCH KLIENTA.");
                    checkListNotNull(CheckList.builder().customer(new Customer()).build(), Filter.DELETE);
                    id = keyboard.nextInt();
                    idIsOnList = customerService.delete(id);
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
                employeeServic.create(new Employee(splitFullName[0], splitFullName[1], role, personalSkill));
                break;
            case 6:
                System.out.println("LISTA PRACOWNIKÓW:");
                checkListNotNull(CheckList.builder().employee(new Employee()).build(), Filter.SHOW);
                break;
            case 7:
                System.out.println("AKTUALIZACJA DANYCH PRACOWNIKA:");
                checkListNotNull(CheckList.builder().employee(new Employee()).build(), Filter.UPDATE);
                id = keyboard.nextInt();
                if (employeeServic.checkId(id)) {
                    System.out.println("Podaj nowe imię i nazwisko pracownika.");
                    keyboard.nextLine();
                    String updateFullName = keyboard.nextLine();
                    String[] splitUpdateFullName = validateFullName(updateFullName);
                    System.out.println("Podaj nowe stanowisko pracownika");
                    role = keyboard.nextLine();
                    System.out.println("Podaj nowe umiejętności pracownika");
                    personalSkill = keyboard.nextLine();
                    employeeServic.update(new Employee(id, splitUpdateFullName[0], splitUpdateFullName[1], role, personalSkill));
                } else {
                    System.out.println("Brak klienta o podanym id");
                }
                break;
            case 8:
                do {
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("USUNIĘCIE DANYCH PRACOWNIKA.");
                    checkListNotNull(CheckList.builder().employee(new Employee()).build(), Filter.DELETE);
                    id = keyboard.nextInt();
                    idIsOnList = employeeServic.delete(id);
                } while (idIsOnList == false);
                break;
            case 9:
                System.out.println("DODAWANIE POKOJU:");
                keyboard.nextLine();
                System.out.println("Dodaj numer pokoju");
                roomNumber = keyboard.nextInt();
                System.out.println("Dodaj rozmiar pokoju");
                roomSize = keyboard.nextInt();
                keyboard.nextLine();
                System.out.println("Dodaj sprzęt w pokoju");
                equipment = keyboard.nextLine();

                System.out.println("Podaj cenę wynajęcia pokoju");
                price = keyboard.nextBigDecimal();
                roomService.create(new Room(roomNumber, roomSize, equipment, price));
                break;
            case 10:
                System.out.println("POKOJE:");
                checkListNotNull(CheckList.builder().room(new Room(roomNumber, roomSize, equipment, price)).build(), Filter.SHOW);
                break;
            case 11:
                System.out.println("AKTUALIZACJA DANYCH O POKOJACH:");
                checkListNotNull(CheckList.builder().room(new Room(roomNumber, roomSize, equipment, price)).build(), Filter.UPDATE);
                id = keyboard.nextInt();
                if (roomService.checkId(id)) {
                    System.out.println("Podaj nowy numer pokoju.");
                    roomNumber = keyboard.nextInt();
                    System.out.println("Podaj nowy rozmiar pokoju.");
                    roomSize = keyboard.nextInt();
                    keyboard.nextLine();
                    System.out.println("Podaj nowe wyposażenie pokoju");
                    equipment = keyboard.nextLine();
                    System.out.println("Podaj nową cenę pokoju");
                    price = keyboard.nextBigDecimal();
                    roomService.update(new Room(roomNumber, roomSize, equipment, price));
                } else {
                    System.out.println("Brak pokoju o podanym id");
                }
                break;
            case 12:
                do {
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("USUNIĘCIE POKOJU.");
                    checkListNotNull(CheckList.builder().room(new Room(roomNumber, roomSize, equipment, price)).build(), Filter.DELETE);
                    id = keyboard.nextInt();
                    idIsOnList = roomService.delete(id);
                } while (idIsOnList == false);
                break;
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
            if (employeeServic.findAll() != null) {
                if (!employeeServic.findAll().isEmpty()) {
                    if (Filter.DELETE.equals(filter)) {
                        System.out.println("Wybierz pracownika, którego dane chcesz usunąć.");
                    }
                    if (Filter.UPDATE.equals(filter)) {
                        System.out.println("Wybierz pracownika, którego dane chcesz zmienić.");
                    }
                    employeeServic.findAll().forEach(customer -> {
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
                roomService.findAll().forEach(customer -> {
                    System.out.println(customer.toString());
                });
            } else {
                System.out.println("Brak listy pokoi.");
                showMenu();
            }
        } else {
            System.out.println("Brak listy pokoi.");
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
