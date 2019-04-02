package feinno.com.myapplication;

import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import feinno.com.myapplication.matcher.MobileMatcher;
import feinno.com.myapplication.rule.MyRule;

import static org.hamcrest.core.CombinableMatcher.both;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.*;

/**
 * 项目名：MyApplication - Android客户端
 * 描述：
 *
 * @author zhangfangmin on 2019/4/2
 * @version 1.0
 * @since JDK1.8.0_152
 */
public class DateUtilsTest {

    private final String TAG = DateUtilsTest.class.getSimpleName();

    private String mTime = "2019-04-02 10:44:06";

    private long mTimeStamp = 1554173046019L;

    private Date mDate;
//
//    @Before
//    public void beforeTest() {
//        System.out.print(TAG + "beforeTest");
//        mDate = new Date();
//        mDate.setTime(mTimeStamp);
//    }
//
//    @After
//    public void afterTest() {
//        System.out.print(TAG + "afterTest");
//    }

    @Rule
    public MyRule mMyRule = new MyRule();

    @Test
    public void dateToStamp() throws ParseException {
        System.out.print(TAG + "dateToStamp");
        assertNotEquals("预期结果", DateUtils.dateToStamp(mTime));
    }

    @Test
    public void stampToDate() {
        System.out.print(TAG + "stampToDate");
        assertNotEquals(4, DateUtils.stampToDate(mTimeStamp));
    }

    @Test(expected = ParseException.class)
    public void dateToStampTest() throws Exception {
        System.out.print(TAG + "dateToStampTest");
        DateUtils.dateToStamp("2019-04-02");
    }

    @Test
    public void testAssertThat() {
        System.out.print(TAG + "testAssertThat");
        assertThat("Hello World!", both(startsWith("Hello")).and(endsWith("World")));
    }

    @Test
    public void testAssertThat2() {
        System.out.print(TAG + "testAssertThat2");
        assertThat("1981814197312", new MobileMatcher());
    }


}