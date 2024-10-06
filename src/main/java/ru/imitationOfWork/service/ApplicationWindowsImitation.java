package ru.imitationOfWork.service;

import lombok.Getter;
import ru.imitationOfWork.config.PropertiesLoader;
import ru.imitationOfWork.util.ErrorText;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@Getter
public class ApplicationWindowsImitation {

    private final List<String> programsList;
    private final int delayOpenApplication;

    public ApplicationWindowsImitation() {
        Properties properties = new PropertiesLoader().loadProperty();
        this.delayOpenApplication = Integer.parseInt(properties.getProperty("application.windows.delay"));
        this.programsList = prepareProgramsList(properties);
    }

    public void run(Integer minutes) {
        final LocalDateTime expiredTime = minutes == null ? LocalDateTime.MAX : LocalDateTime.now().plusMinutes(minutes);

        while (LocalDateTime.now().isBefore(expiredTime)) {
            processRun();
        }
    }

    private void processRun() {
        Random random = new Random();
        int indexProgram = random.nextInt(programsList.size());

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(programsList.get(indexProgram));
            Process process = processBuilder.start();
            Thread.sleep(delayOpenApplication);
            process.destroy();
        } catch (IOException | InterruptedException e) {
            System.out.println(ErrorText.UNEXPECTED_SITUATION);
        }
    }

    private List<String> prepareProgramsList(Properties properties) {
        return Arrays.stream(properties.getProperty("application.windows.programs")
                        .split(properties.getProperty("application.windows.programs.delimiter")))
                .toList();
    }
}