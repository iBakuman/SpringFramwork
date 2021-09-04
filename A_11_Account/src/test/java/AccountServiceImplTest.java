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
}
