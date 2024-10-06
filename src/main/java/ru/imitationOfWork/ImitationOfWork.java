package ru.imitationOfWork;

import ru.imitationOfWork.service.ApplicationWindowsImitation;
import ru.imitationOfWork.service.MouseMover;
import ru.imitationOfWork.util.ErrorText;
import ru.imitationOfWork.util.MenuText;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ImitationOfWork {
    public static void main(String[] args) {
        mainModeMenu(new Scanner(System.in));
    }

    private static void mainModeMenu(Scanner scanner) {
        while (true) {
            System.out.println("To exit the program at any time, press \"ctrl+c\"");
            modeMenuCommand();
            if (scanner.hasNextInt()) {
                int commandMode = scanner.nextInt();
                switch (commandMode) {
                    case 1:
                        mainMethodMenu(scanner, null);
                        break;
                    case 2:
                        mainMinutesMenu(scanner);
                        break;
                    case 0:
                        scanner.close();
                        return;
                    default:
                        System.out.println(ErrorText.ERROR_COMMAND_MENU);
                        break;
                }
            } else {
                scanner.next();
                System.out.println(ErrorText.ERROR_COMMAND_MENU);
            }
        }
    }

    private static void mainMethodMenu(Scanner scanner, Integer minutes) {
        MouseMover mouseMover = new MouseMover();
        ApplicationWindowsImitation windowsImitation = new ApplicationWindowsImitation();

        while (true) {
            methodMenuCommand();
            if (scanner.hasNextInt()) {
                int commandMethod = scanner.nextInt();
                switch (commandMethod) {
                    case 1:
                        mouseMover.run(minutes);
                        break;
                    case 2:
                        System.out.println("List of programs names:" + windowsImitation.getProgramsList());
                        windowsImitation.run(minutes);
                        break;
                    case 3:
                        ExecutorService executor = Executors.newFixedThreadPool(2);
                        executor.execute(() -> mouseMover.run(minutes));
                        executor.execute(() -> windowsImitation.run(minutes));
                        executor.shutdown();
                        try {
                            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                        } catch (InterruptedException e) {
                            System.out.println(ErrorText.UNEXPECTED_SITUATION);
                        }
                        break;
                    case 0:
                        mainModeMenu(scanner);
                        return;
                    default:
                        System.out.println(ErrorText.ERROR_COMMAND_MENU);
                        break;
                }
                System.out.println("\nWork is finished\n");
            } else {
                scanner.next();
                System.out.println(ErrorText.ERROR_COMMAND_MENU);
            }
        }
    }

    private static void mainMinutesMenu(Scanner scanner) {
        while (true) {
            System.out.println(MenuText.COMMAND_INPUT_MINUTES);
            if (scanner.hasNextInt()) {
                mainMethodMenu(scanner, scanner.nextInt());
            } else {
                scanner.next();
                System.out.println(ErrorText.UNEXPECTED_SITUATION);
            }
        }
    }

    private static void methodMenuCommand() {
        System.out.println(MenuText.METHOD_MENU_MAIN_TEXT);
        System.out.println(MenuText.METHOD_MENU_CURSOR);
        System.out.println(MenuText.METHOD_MENU_NOTEBOOK);
        System.out.println(MenuText.METHOD_MENU_ALL);
        System.out.println(MenuText.MENU_EXIT);
    }

    private static void modeMenuCommand() {
        System.out.println(MenuText.MODE_MENU_MAIN_TEXT);
        System.out.println(MenuText.MODE_MENU_WITH_OUT_TIME);
        System.out.println(MenuText.MODE_MENU_WITH_TIME);
        System.out.println(MenuText.MENU_EXIT);
    }
}