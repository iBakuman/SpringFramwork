import com.example.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试数据AccountServiceImpl类
 *
 * @author Avarice
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceImplTest {

    @Autowired
    private AccountService service;

    @Test
    public void testTransfer() {
        service.transfer("ccc", "bbb", 100.0f);
    }

    @Test
    public void test() {
        AccountServiceImplTest.testFinally();
    }

    public static int testFinally() {
        try {
            String str = "hello world";
            System.out.println(str);
            return 1;
        } catch (Exception e) {
            System.out.println("Arithmetical Exception");
        } finally {
            System.out.println("finally");
        }
        return 1;
    }
}
