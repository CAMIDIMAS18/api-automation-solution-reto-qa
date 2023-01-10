package org.api.testing.demo.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import org.api.testing.demo.exceptions.GenericRuntimeException;

/**
 * A Screenplay ability that allows an actor to perform authenticate with username and password.
 * For example:
 * ```
 * Actor sam = Actor.named("Sam the supervisor").whoCan(Authenticate.with("admin", "password123"));
 * ```
 */
public class Authenticate implements Ability {
    private final String username;
    private final String password;

    private Authenticate(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Authenticate with(String username, String password) {
        return new Authenticate(username, password);
    }

    public static Authenticate as(Actor actor) {
        if (actor.abilityTo(Authenticate.class) == null) {
            throw new GenericRuntimeException(actor.getName());
        }
        return actor.abilityTo(Authenticate.class);
    }

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }

    @Override
    public String toString() {
        return "Authenticate with username and password";
    }
}

