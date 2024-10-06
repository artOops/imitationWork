package ru.imitationOfWork.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorText {

    ERROR_COMMAND_MENU("Incorrect command!"),
    UNEXPECTED_SITUATION("Oops, something went wrong..."),
    NO_CLOSE_PROCESS("Сould not close the process..."),
    FAILED_READ_FILE_PROPERTIES("Failed to read configuration file");

    private final String text;

    @Override
    public String toString() {
        return text;
    }
}
