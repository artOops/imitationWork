package ru.imitationOfWork.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MoveCursorData {
    private double xCurrent;
    private double yCurrent;
    private double xNew;
    private double yNew;
    private int countSteps;
    private double xLengthStep;
    private double yLengthStep;
}
