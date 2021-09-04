import com.example.config.SpringConfig;
import com.example.domain.Account;
import com.example.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Spring整合Junit的配置
 *  1.导入spring整合junit的坐标(artifactId: sprint-test)，注意：spring-test的版本必须要和sprint-context的版本相同，不然会出错
 *  2.使用junit提供的一个注解把原有的main方法替换掉，调换成Spring提供的
 *      使用注解@Runwith来指定main方法所在类，Spring提供了该类，名称为SpringJUnit4ClassRunner
 *      参数为一个字节码文件
 *  3.告知Spring的运行器，spring和ioc创建是基于xml还是注解，并且说明位置
 *      ContextConfiguration
 *          location：指定xml文件的位置，加上classpath关键字，表示在类路径下
 *          classes：指定注解类（配置类）所在的位置
 *  注意：当我们使用spring 5.x版本的时候，要求junit的版本必须是4.12及以上
 * @author Avarice
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class AccountServiceImplTest {
    @Autowired
    private AccountService accountService = null;

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() {
        List<Account> accounts = accountService.findAll();
        for (Account account : accounts)
            System.out.println(account);
    }

    /**
     * 测试根据id查询
     */
    @Test
    public void testFindById() {
        Account account = accountService.findById(1);
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

        accountService.saveAccount(account);
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

        accountService.updateAccount(account);
    }

    /**
     * 测试删除数据
     */
    @Test
    public void testDelete() {
        accountService.deleteAccount(4);
    }
}
