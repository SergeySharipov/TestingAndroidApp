package ca.sharipov.testingandroidapp

import com.google.common.truth.Truth.assertThat

import org.junit.Test

class RegistrationUtilTest {
    /**
     *  The input is not valid if...
     *  ...the confirmed password is not the same as the real password
     *  ...the password contains less than 2 digits
     */

    @Test
    fun `empty username returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "",
            ""
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `the password was repeated incorrectly returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "123",
            "124"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Sergey",
            "123",
            "123"
        )

        assertThat(result).isTrue()
    }

    @Test
    fun `username already exists returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "123",
            "123"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `password does not contain 2 digits returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "aa1",
            "aa1"
        )

        assertThat(result).isFalse()
    }
}