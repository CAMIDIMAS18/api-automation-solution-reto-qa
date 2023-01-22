package org.api.testing.demo.assertions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.demo.questions.common.GetResponseMessageQuestion.responseMessageIs;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EnsureResponseBodyStringTask implements Task {
    private final String nameResponse;

    public EnsureResponseBodyStringTask(String nameResponse) {
        this.nameResponse = nameResponse;
    }

    public static EnsureResponseBodyStringTask ensureThatResponseMessageMatchesWith(String nameResponse) {
        return instrumented(EnsureResponseBodyStringTask.class, nameResponse);
    }

    @Override
    @Step("Then the response message matches with '#nameResponse'")
    public <T extends Actor> void performAs(T actor) {
        assertThat(responseMessageIs().answeredBy(actor), equalTo(nameResponse));
    }
}