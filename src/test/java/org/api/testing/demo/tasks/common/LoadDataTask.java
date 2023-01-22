package org.api.testing.demo.tasks.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.Step;
import org.api.testing.demo.models.common.BookingData;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LoadDataTask implements Task {
    private final Map<String, String> testData;

    public LoadDataTask(Map<String, String> testData) {
        this.testData = testData;
    }

    public static LoadDataTask informationBooking(Map<String, String> testData) {
        return Tasks.instrumented(LoadDataTask.class, testData);
    }

    @Override
    @Step("Data uploaded!")
    public <T extends Actor> void performAs(T actor) {
        Set<Map.Entry<String, String>> testDataAux = testData.entrySet();
        BookingData.setData(testDataAux.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }
}
