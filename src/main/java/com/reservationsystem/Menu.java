package com.reservationsystem;

import com.reservationsystem.config.CheckList;
import com.reservationsystem.direction.Filter;
import com.reservationsystem.dto.Customer;
import com.reservationsystem.dto.Employee;
import com.reservationsystem.dto.Reservation;
import com.reservationsystem.dto.Room;
import com.reservationsystem.service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Menu {

    private boolean exitApplication = true;
    private Scanner keyboard = new Scanner(System.in);
    private CustomerServiceInterface customerService = new CustomerService();
    private EmployeeServiceInterface employeeService = new EmployeeService();
    private RoomServiceInterface roomService = new RoomService();
    private ReservationServiceInterface reservationService = new ReservationService(roomService);
    private int id;
    private String fullName;
    private String[] splitFullName;
    private String role;
    private String personalSkill;
    private Boolean idIsOnList;
    private LocalDate startDate;
    private LocalDate endDate;
    private int roomNumber;
    private int roomSize;
    private String equipment;
    private BigDecimal price;
    private BigDecimal sum;


    public void menu() {
        System.out.println("RESERVATION SYSTEM");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Wybierz jedną z opcji");
        System.out.println("(0) Wyjście z aplikacji");
        System.out.println("OPCJE KLIENTA");
        System.out.println("(1) Dodaj dane klienta");
        System.out.println("(2) Pokaż wszystkich klientów");
        System.out.println("(3) Zaktualizuj dane klienta");
        System.out.println("(4) Usuń klienta");
        System.out.println("OPCJE PRACOWNIKA");
        System.out.println("(5) Dodaj dane pracownika");
        System.out.println("(6) Pokaż wszystkich pracowników");
        System.out.println("(7) Zaktualizuj dane pracownika");
        System.out.println("(8) Usuń pracownika");
        System.out.println("REZERWACJA");
        System.out.println("(9) Dodaj rezerwację");
        System.out.println("(10) Pokaż listę rezerwacji");
        System.out.println("(11) Zmień rezerwację");
        System.out.println("(12) Usuń rezerwację");
        System.out.println("POKOJE");
        System.out.println("(13) Dodaj pokój");
        System.out.println("(14) Pokaż listę pokoi");
        System.out.println("(15) Aktualizuj dane pokoju");
        System.out.println("(16) Usuń pokój");

        int index = keyboard.nextInt();
        switch (index) {
            case 0:
                exitApplication = false;
                break;
            case 1:
                System.out.println("DODAWANIE KLIENTA:");
                keyboard.nextLine();
                createCustomer();
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
                } while (idIsOnList == false);
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
                id = keyboard.nextInt();
                if (employeeService.checkId(id)) {
                    System.out.println("Podaj nowe imię i nazwisko pracownika.");
                    keyboard.nextLine();
                    String updateFullName = keyboard.nextLine();
                    String[] splitUpdateFullName = validateFullName(updateFullName);
                    System.out.println("Podaj nowe stanowisko pracownika");
                    role = keyboard.nextLine();
                    System.out.println("Podaj nowe umiejętności pracownika");
                    personalSkill = keyboard.nextLine();
                    employeeService.update(new Employee(id, splitUpdateFullName[0], splitUpdateFullName[1], role, personalSkill));
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
                    idIsOnList = employeeService.delete(id);
                } while (idIsOnList == false);
                break;
            case 9:
                keyboard.nextLine();
                System.out.println("DODAWANIE REZERWACJI:");
                System.out.println("Podaj numer klienta lub stwórz nowego.");
                customerService.findAll().stream()
                        .forEach(customer -> {
                            System.out.println(customer);
                        });
                System.out.println("Aby stworzyć klienta wciśnij 0.");
                int customerId = keyboard.nextInt();
                if (customerId == 0) {
                    customerId = createCustomer();
                }
                System.out.println("Podaj datę rozpoczęcia rezerwacji");
                startDate = createDate();
                System.out.println("Podaj datę zakończenia rezerwacji");
                endDate = createDate();
                System.out.println("Podaj id pokoju.");
                roomService.findAll().stream()
                        .forEach(room -> {
                            System.out.println(room);
                        });
                int roomId = keyboard.nextInt();
                if (roomId == 0) {
                    roomId = createRoom();
                }
                sum = reservationService.sum(roomId, startDate, endDate);
                System.out.println("Całkowita kwota wynajmu pokoju: " + sum + " zł");
                System.out.println("Wpisz sumę wpłaconego depozytu przez klienta.");
                BigDecimal deposit = keyboard.nextBigDecimal();
                String answer = isFullPaid(deposit);
                System.out.println("Wybierz pracownika, który dokonał rezerwacji.");
                employeeService.findAll().stream()
                        .forEach(employee -> {
                            System.out.println(employee);
                        });
                int employeeId = keyboard.nextInt();
                reservationService.create(Reservation.builder()
                        .customerId(customerId)
                        .startReservationDate(startDate)
                        .endReservationDate(endDate)
                        .sum(sum)
                        .deposit(deposit)
                        .isFullPaid(answer)
                        .employerId(employeeId)
                        .build());
                break;
            case 10:
                System.out.println("LISTA REZERWACJI:");
                checkListNotNull(CheckList.builder().reservation(new Reservation()).build(), Filter.SHOW);
                break;
            case 11:
                LocalDate updateStartDate;
                LocalDate updateEndDate;
                BigDecimal updateSum;
                BigDecimal updateDeposit;
                System.out.println("AKTUALIZACJA DANYCH REZERWACJI:");
                checkListNotNull(CheckList.builder().reservation(new Reservation()).build(), Filter.UPDATE);
                id = keyboard.nextInt();
                if (reservationService.checkId(id)) {
                    System.out.println("Podaj nową datę rozpoczęcia rezerwacji.");
                    updateStartDate = createDate();
                    System.out.println("Podaj nową datę zakończenia rezerwacji.");
                    updateEndDate = createDate();
                    System.out.println("Podaj nowe id pokoju.");
                    roomService.findAll().stream()
                            .forEach(room -> {
                                System.out.println(room);
                            });
                    int updateRoomId = keyboard.nextInt();
                    if (updateRoomId == 0) {
                        updateRoomId = createRoom();
                    }
                    updateSum = reservationService.sum(updateRoomId, updateStartDate, updateEndDate);
                    System.out.println("Całkowita kwota wynajmu pokoju: " + updateSum + " zł");
                    System.out.println("Podaj nową kwotę wpłaty klienta.");
                    updateDeposit = keyboard.nextBigDecimal();
                    String updateAnswer = isFullPaid(updateDeposit);
                    Reservation reservation = reservationService.getById(id);
                    reservationService.update(Reservation.builder()
                        .customerId(reservation.getCustomerId())
                        .startReservationDate(updateStartDate)
                        .endReservationDate(updateEndDate)
                        .sum(updateSum)
                        .deposit(updateDeposit)
                        .isFullPaid(updateAnswer)
                        .employerId(reservation.getEmployerId())
                        .build());
        } else{
            System.out.println("Brak rezerwacji o podanym id");
    }
               break;
            case 12:
               do {
                   System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("USUNIĘCIE REZERWACJI.");
                   checkListNotNull(CheckList.builder().reservation(new Reservation()).build(), Filter.DELETE);
                    id = keyboard.nextInt();
                    idIsOnList = reservationService.delete(id);
               } while (idIsOnList == false);
                break;
            case 13:
                System.out.println("DODAWANIE POKOJU:");
                keyboard.nextLine();
                createRoom();
                break;
            case 14:
                System.out.println("LISTA POKOI:");
                checkListNotNull(CheckList.builder().room(new Room()).build(), Filter.SHOW);
                break;
            case 15:
                System.out.println("AKTUALIZACJA DANYCH POKOJU:");
                checkListNotNull(CheckList.builder().room(new Room()).build(), Filter.UPDATE);
                id = keyboard.nextInt();
                if (roomService.checkId(id)) {
                    System.out.println("Podaj numer pokoju");
                    int updateRoomNumber = keyboard.nextInt();
                    System.out.println("Podaj ile osób pomieści pokój");
                    int updateRoomSize = keyboard.nextInt();
                    keyboard.nextLine();
                    System.out.println("Podaj wyposażenie pokoju.");
                    String updateEquipment = keyboard.nextLine();
                    System.out.println("Podaj cenę pokoju");
                    BigDecimal updatePrice = keyboard.nextBigDecimal();
                    roomService.update(new Room(updateRoomNumber, updateRoomSize, updateEquipment, updatePrice));
                } else {
                    System.out.println("Brak pokojów o podanym id");
                }
                break;
            case 16:
                do {
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("USUNIĘCIE POKOJU.");
                    checkListNotNull(CheckList.builder().room(new Room()).build(), Filter.DELETE);
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
        } else if (checkList.getReservation() != null) {
            if (reservationService.findAll() != null) {
                if (!reservationService.findAll().isEmpty()) {
                    if (Filter.DELETE.equals(filter)) {
                        System.out.println("Wybierz rezerwację, której dane chcesz usunąć.");
                    }
                    if (Filter.UPDATE.equals(filter)) {
                        System.out.println("Wybierz rezerwacje, której dane chcesz zmienić.");
                    }
                    reservationService.findAll().forEach(customer -> {
                        System.out.println(customer.toString());
                    });
                } else {
                    System.out.println("Brak listy rezerwacji.");
                    showMenu();
                }
            } else {
                System.out.println("Brak listy rezerwacji.");
                showMenu();
            }
        } else if (checkList.getRoom() != null) {
            if (roomService.findAll() != null) {
                if (!roomService.findAll().isEmpty()) {
                    if (Filter.DELETE.equals(filter)) {
                        System.out.println("Wybierz pokój, którego dane chcesz usunąć.");
                    }
                    if (Filter.UPDATE.equals(filter)) {
                        System.out.println("Wybierz pokój, którego dane chcesz zmienić.");
                    }
                    roomService.findAll().forEach(customer -> {
                        System.out.println(customer.toString());
                    });
                } else {
                    System.out.println("Brak pokoi.");
                    showMenu();
                }
            } else {
                System.out.println("Brak pokoi.");
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
    private int createCustomer() {
        System.out.println("Dodaj imię i nazwisko");
        fullName = keyboard.nextLine();
        splitFullName = validateFullName(fullName);
        System.out.println("Dodaj numer PESEL");
        String pesel = keyboard.nextLine();
        int id = customerService.create(new Customer(splitFullName[0], splitFullName[1], pesel));
        return id;
    }

    private int createRoom() {
        System.out.println("Podaj numer pokoju");
        roomNumber = keyboard.nextInt();
        System.out.println("Podaj ile osób pomieści pokój.");
        roomSize = keyboard.nextInt();
        System.out.println("Podaj wyposażenie pokoju");
        keyboard.nextLine();
        equipment = keyboard.nextLine();
        System.out.println("Podaj cenę za dobę");
        price = keyboard.nextBigDecimal();
        int id = roomService.create(new Room(roomNumber, roomSize, equipment, price));
        return id;
    }

    private LocalDate createDate() {
        System.out.println("Podaj dzień");
        int day = keyboard.nextInt();
        System.out.println("Podaj miesiąc");
        int month = keyboard.nextInt();
        System.out.println("Podaj rok");
        int year = keyboard.nextInt();
        keyboard.nextLine();
       return LocalDate.of(year, month, day);
    }

    private String isFullPaid(BigDecimal deposit) {
        if(deposit.compareTo(sum) < 0) {
            return "nie";
        } else {
            return "tak";
        }
    }
}
