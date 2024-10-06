package ru.imitationOfWork.service;

import ru.imitationOfWork.config.PropertiesLoader;
import ru.imitationOfWork.model.MoveCursorData;
import ru.imitationOfWork.util.ErrorText;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;

public class MouseMover {
    private final double screenWidth;
    private final double screenHeight;
    private final int delayStepMillisecond;
    private final int delayMoveCursorMillisecond;

    public MouseMover() {
        Properties properties = new PropertiesLoader().loadProperty();
        this.screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        this.screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.delayStepMillisecond = Integer.parseInt(properties.getProperty("mouse.mover.step.delay"));
        this.delayMoveCursorMillisecond = Integer.parseInt(properties.getProperty("mouse.mover.cursor.delay"));
    }

    public void run(Integer minutes) {
        final LocalDateTime expiredTime = minutes == null ? LocalDateTime.MAX : LocalDateTime.now().plusMinutes(minutes);

        while (LocalDateTime.now().isBefore(expiredTime)) {
            moveCursor();
        }
    }

    private void moveCursor() {
        try {
            Robot robot = new Robot();
            MoveCursorData data = getNewDataMove();

            for (int step = 0; step < data.getCountSteps(); step++) {
                int x = (int) (data.getXCurrent() + data.getXLengthStep() * step);
                int y = (int) (data.getYCurrent() + data.getYLengthStep() * step);

                robot.mouseMove(x, y);
                robot.delay(delayStepMillisecond);
            }
            robot.delay(delayMoveCursorMillisecond);
        } catch (AWTException e) {
            System.out.println(ErrorText.UNEXPECTED_SITUATION);
        }
    }

    private MoveCursorData getNewDataMove() {
        Random random = new Random();

        double xCurrent = MouseInfo.getPointerInfo().getLocation().getX();
        double yCurrent = MouseInfo.getPointerInfo().getLocation().getY();

        double xNew = random.nextDouble(screenWidth);
        double yNew = random.nextDouble(screenHeight);

        int countSteps = getCountSteps((getLength(xCurrent, yCurrent, xNew, yCurrent)));

        double dx = (xNew - xCurrent) / countSteps;
        double dy = (yNew - yCurrent) / countSteps;

        return MoveCursorData.builder()
                .xCurrent(xCurrent)
                .yCurrent(yCurrent)
                .xNew(xNew)
                .yNew(yNew)
                .countSteps(countSteps)
                .xLengthStep(dx)
                .yLengthStep(dy)
                .build();
    }

    private int getCountSteps(double length) {
        return (int) length / delayStepMillisecond;
    }

    private double getLength(double xStart, double yStart, double xEnd, double yEnd) {
        return Math.sqrt(Math.pow(xEnd - xStart, 2) + Math.pow(yEnd - yStart, 2));
    }
}