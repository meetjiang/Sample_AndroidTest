package feinno.com.myapplication.rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * 项目名：MyApplication - Android客户端
 * 描述：
 *
 * @author zhangfangmin on 2019/4/2
 * @version 1.0
 * @since JDK1.8.0_152
 */
public class MyRule implements TestRule {

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                // evaluate前执行方法相当于@Before
                String methodName = description.getMethodName();
                System.out.print(methodName + "开始测试\n");

                // 运行的测试方法
                base.evaluate();

                // evaluate后执行方法相当于@After
                System.out.println("\n" + methodName + "测试结束！");
            }
        };
    }

}
