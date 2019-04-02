package feinno.com.myapplication.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名：MyApplication - Android客户端
 * 描述：
 *
 * @author zhangfangmin on 2019/4/2
 * @version 1.0
 * @since JDK1.8.0_152
 */
public class MobileMatcher extends BaseMatcher<String> {


    @Override
    public boolean matches(Object item) {
        if (item == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("(1|861)(3|5|7|8)\\d{9}$*");
        Matcher matcher = pattern.matcher((String) item);
        return matcher.find();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("预计此字符串是手机号码！");
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        description.appendText(item.toString() + "不是手机号码！");
    }
}
