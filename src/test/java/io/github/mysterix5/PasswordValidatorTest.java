package io.github.mysterix5;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    @org.junit.jupiter.api.Test
    void verify_password() {
        String password_input = "asdf";
        var pv = new PasswordValidator();
        boolean actual = pv.verify_password(password_input);

        assertFalse(actual);
    }
}