package feinno.com.myapplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

/**
 * 项目名：MyApplication - Android客户端
 * 描述：
 *
 * @author zhangfangmin on 2019/4/2
 * @version 1.0
 * @since JDK1.8.0_152
 */
@RunWith(Parameterized.class)
public class DateFormatTest {

    private final String TAG = DateFormatTest.class.getSimpleName();

    private String mTime;

    public DateFormatTest(String time) {
        this.mTime = time;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new String[]{"2019-04-02", "2019-04-02 10:44:06", "2019年04月02日 10时44分06秒"});
    }

    @Test(expected = ParseException.class)
    public void dateToStampTest() throws Exception {
        System.out.print(TAG + "dateToStampTest");
        DateUtils.dateToStamp(mTime);
    }
}
