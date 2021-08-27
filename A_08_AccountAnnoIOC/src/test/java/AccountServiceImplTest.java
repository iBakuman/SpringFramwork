import com.example.domain.Account;
import com.example.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

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

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() {
        AccountService service = context.getBean("accountService", AccountService.class);
        List<Account> accounts = service.findAll();
        for (Account account : accounts)
            System.out.println(account);
    }

    /**
     * 测试根据id查询
     */
    @Test
    public void testFindById() {
        AccountService service = context.getBean("accountService", AccountService.class);
        Account account = service.findById(1);
        System.out.println(account);
    }

    /**
     * 测试保存数据
     */
    @Test
    public void testSave() {
        Account account = new Account();
        account.setMoney(new Float(100.0));
        account.setName("dddd");

        AccountService service = context.getBean("accountService", AccountService.class);
        service.saveAccount(account);
    }

    /**
     * 测试更新数据
     */
    @Test
    public void testUpdate() {
        Account account = new Account();
        account.setId(1);
        account.setName("龚胜辉");
        account.setMoney(new Float(100000.0));

        AccountService service = context.getBean("accountService", AccountService.class);
        service.updateAccount(account);
    }

    /**
     * 测试删除数据
     */
    @Test
    public void testDelete() {
        AccountService service = context.getBean("accountService", AccountService.class);
        service.deleteAccount(4);
    }
}
