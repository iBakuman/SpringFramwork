import com.example.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试数据AccountServiceImpl类
 * @author Avarice
 */
public class AccountServiceImplTest {
    private ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("bean.xml");
    }

    @Test
    public void testTransfer() {
        AccountService service = context.getBean("accountService", AccountService.class);
        service.transfer("ccc", "bbb", 100.0f);
    }

    @Test
    public void test() {
        AccountServiceImplTest.testFinally();
    }

    public static int  testFinally() {
        try {
            String str = "hello world";
            System.out.println(str);
            return 1;
        }catch (Exception e){
            System.out.println("Arithmetical Exception");
        }finally {
            System.out.println("finally");
        }
        return 1;
    }
}
