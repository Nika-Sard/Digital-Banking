import com.example.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BasicModelTests {
    @Test
    public void testUser() {
        User user = new User("name1", "last name1", "1");
    }
}
