import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CredentialsTest {

    private Pattern pattern = Pattern.compile("\\S+@\\S+\\.\\S+");

    @Test
    public void emailCorrectness1() {
        assertFalse(pattern.matcher("input").matches());
    }

    @Test
    public void emailCorrectness2() {
        assertTrue(pattern.matcher("input@zone.ro").matches());
    }

    @Test
    public void emailCorrectness3() {
        assertFalse(pattern.matcher("input@nODOT").matches());
    }

    @Test
    public void passwordConsistency1() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass1 = encoder.encode("mypassword");
        assertTrue(pass1.equals(encoder.encode("mypassword")));
    }
}
