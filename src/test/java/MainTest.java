import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestClient.class,
        TestBuy.class,
        TestSell.class,
        TestProcessing.class
})
public class MainTest {

    public static void main(String[] args) {

        Result result = JUnitCore.runClasses(MainTest.class);

        System.out.println("getRunCount = " + result.getRunCount());
        System.out.println("wasSuccessful = " + result.wasSuccessful());
        for (Failure failure : result.getFailures()) {
            System.out.println("failure = " + failure.toString());
        }
    }
}
