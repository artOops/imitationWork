package ru.imitationOfWork.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuText {

    METHOD_MENU_MAIN_TEXT(null, "Select method:"),
    METHOD_MENU_CURSOR(1, "move cursor mouse"),
    METHOD_MENU_NOTEBOOK(2, "open/close programs"),
    METHOD_MENU_ALL(3, "all together"),
    MENU_EXIT(0, "exit"),
    MODE_MENU_MAIN_TEXT(null, "Select mode:"),
    MODE_MENU_WITH_OUT_TIME(1, "no time limit"),
    MODE_MENU_WITH_TIME(2, "with time limit"),
    COMMAND_INPUT_MINUTES(null, "Write number of minutes");

    private final Integer numberCommand;
    private final String text;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (numberCommand != null) {
            result.append(numberCommand).append(" - ");
        }

        return result.append(text).toString();
    }
}
