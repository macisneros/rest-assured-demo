package utils;

import lombok.SneakyThrows;
import org.testng.ITestContext;
import org.testng.ITestListener;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hook implements ITestListener {

    private String folderName;

    @Override
    public void onStart(ITestContext context) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_SSSS_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        folderName = dtf.format(now);
        new File("report/" + folderName).mkdirs();
    }

    @SneakyThrows
    @Override
    public void onFinish(ITestContext context) {
        String allureCmd = System.getProperty("user.home") + "/scoop/shims/allure.cmd";
        Runtime.getRuntime().exec("cmd /c c: && " + allureCmd + " generate target/allure-results -o report/" + folderName);
    }
}
