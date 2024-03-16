package com.reservationsystem;

import com.reservationsystem.dto.Customer;
import com.reservationsystem.exception.WrongNameException;
import com.reservationsystem.service.CustomerService;

import java.util.Scanner;

public class Menu {

    private boolean exitApplication = true;
    private Scanner keyboard = new Scanner(System.in);
    private CustomerService customerService = new CustomerService();
    private int id;

    public void menu() {
        System.out.println("RESERVATION SYSTEM");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Wybierz jedną z opcji");
        System.out.println("(0) Wyjście z aplikacji");
        System.out.println("(1) Dodaj dane klienta");
        System.out.println("(2) Pokaż wszystkich klientów");
        System.out.println("(3) Zaktualizuj dane klienta");
        System.out.println("(4) Usuń klienta");

        int index = keyboard.nextInt();
        switch (index) {
            case 0:
                exitApplication = false;
                break;
            case 1:
                keyboard.nextLine();
            System.out.println("Dodaj imię i nazwisko");
                String fullName = keyboard.nextLine();
                String[] splitFullName = validateFullName(fullName);
                System.out.println("Dodaj numer PESEL");
                String pesel = keyboard.nextLine();
                customerService.create(new Customer(splitFullName[0], splitFullName[1], pesel));
                break;
            case 2:
                System.out.println("LISTA KLIENTÓW:");
                customerService.findAll().forEach(customer -> System.out.println(customer.toString()));
                break;
            case 3:
                System.out.println("AKTUALIZACJA DANYCH KLIENTA:");
                customerService.findAll().forEach(customer -> System.out.println(customer.toString()));
                System.out.println("Wybierz klienta, którego dane chcesz zmienić.");
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
                System.out.println("USUNIĘCIE DANYCH KLIENTA."); //todo do poprawy USUNIĘCIE DANYCH
                                                                // (1) co w momencie, gdy usuniemy użytkownika z ID np. 10 -> index ma nadal 10
                                                                // (2) co w momencie, gdy podasz ID którego nie ma
                System.out.println("Wybierz klienta, którego dane chcesz usunąć.");
                customerService.findAll().forEach(customer -> {
                    System.out.println(customer.toString());
                });
                id = keyboard.nextInt();
                customerService.delete(id - 1);
                break;
            default:
                System.out.println("Brak takiej opcji.");
        }
    }

    private String[] validateFullName(String fullName) {
        String[] splitName = new String[2];
        try{
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
