import com.sgxy.supermarket.service.EmployeeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


//@ContextConfiguration(locations = {"classpath:spring.xml"})
@ContextConfiguration(locations = {"classpath:mybatis.xml"})
public class SpringTest {
    //    @Autowired
    //    private EmployeeController employeeController;

    @Autowired
    private EmployeeService employeeService;

    //    1、spring容器管理对象测试完成
    @Test
    public void springTest() {
    //   System.out.println(employeeController);
        System.out.println(employeeService);
    }

}
