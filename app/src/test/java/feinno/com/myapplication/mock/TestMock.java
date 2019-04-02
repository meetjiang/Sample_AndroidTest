package feinno.com.myapplication.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import feinno.com.myapplication.model.User;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * 项目名：MyApplication - Android客户端
 * 描述：mock测试类
 *
 * @author zhangfangmin on 2019/4/2
 * @version 1.0
 * @since JDK1.8.0_152
 */
public class TestMock {

    private List mMockedList = mock(List.class);

    @Mock
    private User mMockedUser;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMock() {
        //使用mock对象
        mMockedList.add("one");
        mMockedList.clear();

        //验证mock对象
        verify(mMockedList).add("one");
        verify(mMockedList).clear();
    }

    @Test
    public void testMockLinkedList() {
        LinkedList mockedLinkedList = mock(LinkedList.class);
        when(mockedLinkedList.get(0)).thenReturn("First");
        when(mockedLinkedList.get(1)).thenReturn(new RuntimeException());

        System.out.print(mockedLinkedList.get(0) + "\n");
        System.out.print(mockedLinkedList.get(1) + "\n");
        System.out.print(mockedLinkedList.get(999) + "\n");

        verify(mockedLinkedList).get(0);
    }

    @Test
    public void argumentMatchersTest() {
        when(mMockedList.get(anyInt())).thenReturn("总是返回这个值");
        System.out.print(mMockedList.get(0) + "\n");
        System.out.print(mMockedList.get(999) + "\n");


        when(mMockedList.addAll(argThat(getListMatcher()))).thenReturn(true);

        boolean b1 = mMockedList.addAll(Arrays.asList("one", "two"));
        boolean b2 = mMockedList.addAll(Arrays.asList("one", "two", "three"));

        verify(mMockedList).addAll(argThat(getListMatcher()));

        Assert.assertTrue(b1);
        Assert.assertTrue(b2);
    }

    /**
     * 测试方法调用次数
     */
    @Test
    public void testInvokeMethodCount() {
        mMockedList.add("once");

        mMockedList.add("twice");
        mMockedList.add("twice");

        mMockedList.add("three times");
        mMockedList.add("three times");
        mMockedList.add("three times");

        verify(mMockedList).add("once");
        //调用1次
        verify(mMockedList, times(1)).add("once");
        //调用2次
        verify(mMockedList, times(2)).add("twice");
        //调用3次
        verify(mMockedList, times(3)).add("three times");

        //从未调用校验
        verify(mMockedList, never()).add("four times");
        verify(mMockedList, atLeastOnce()).add("three times");
        verify(mMockedList, atMost(5)).add("three times");

        //此行代码不过校验通过
        verify(mMockedList, atLeast(2)).add("five times");

    }

    /**
     * 校验方法执行顺序
     */
    @Test
    public void testMethodInvokeOrder() {
        List sigleMock = mock(List.class);
        sigleMock.add("first add");
        sigleMock.add("second add");

        InOrder inOrder = inOrder(sigleMock);

        //inOrder保证方法执行顺序
        inOrder.verify(sigleMock).add("first add");
        inOrder.verify(sigleMock).add("second add");

        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        firstMock.add("first add");
        secondMock.add("second add");

        InOrder inOrder1 = inOrder(firstMock, secondMock);

        //下列代码会确认是否firstMock优先secondMock执行add方法
        inOrder1.verify(firstMock).add("first add");
        inOrder1.verify(secondMock).add("second add");
    }

    /**
     * 测试mock对象未进行过交互
     */
    @Test
    public void testMockInteraction() {
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);
        List thirdMock = mock(List.class);

        firstMock.add("one");
        verify(firstMock).add("one");
        verify(firstMock, never()).add("two");

        firstMock.add(thirdMock);
        verifyZeroInteractions(firstMock);
        verifyZeroInteractions(secondMock, thirdMock);
    }

    @Test
    public void throwException() {
        doThrow(new NullPointerException("空指针异常")).when(mMockedList).clear();
        doThrow(new IllegalArgumentException("参数类型异常")).when(mMockedList).add(anyInt());

        mMockedList.add("test");
        mMockedList.add(12);
        mMockedList.clear();
    }

    /**
     * 测试方法连续调用
     */
    @Test
    public void testContinueInvokeMethod() {
//        when(mMockedUser.getName()).thenReturn("xiaoyuan").
//                thenThrow(new RuntimeException("second invoke exception")).
//                thenReturn("third invoke");
        when(mMockedUser.getName()).thenReturn("xiaoyuan1", "xiaoyuan2", "xiaoyuan3");

        String name1 = mMockedUser.getName();

        try {
            String name2 = mMockedUser.getName();
            System.out.print(name2 + "\n");
        } catch (Exception e) {
            System.out.print(e.getMessage() + "\n");
        }

        String name3 = mMockedUser.getName();

        System.out.print(name1 + "\n");
        System.out.print(name3);
    }

    /**
     * 回调方法测试
     */
    @Test
    public void testCallback() {
        when(mMockedList.add(anyString())).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return false;
            }
        });
        System.out.print(mMockedList.add("第一次返回false"));

        when(mMockedList.add(anyString())).thenReturn(true);
        System.out.print(mMockedList.add("第二次返回true"));

        when(mMockedList.add(anyString())).thenReturn(false);
        System.out.print(mMockedList.add("第三次返回false"));
    }

    /**
     * 监控真实的对象
     */
    @Test
    public void testSpy() {
        List list = new ArrayList();
        List spyList = spy(list);

        when(spyList.size()).thenReturn(100);

        spyList.add("one");
        spyList.add("two");

        System.out.print("获取spyList第1个元素" + spyList.get(0) + "\n");
        System.out.print("获取spyList的size" + spyList.size() + "\n");

        verify(spyList).add("one");
        verify(spyList).add("two");

        doReturn("ten").when(spyList).get(9);
        doReturn("eleven").when(spyList).get(10);

        System.out.print("spyList第10个元素" + spyList.get(9) + "\n");
        System.out.print("spyList第11个元素" + spyList.get(10));
    }

    /**
     * 捕获参数
     */
    @Test
    public void captorMethodParam() {
        User user = new User();
        user.setName("microfountain");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        mMockedList.add(user);
        verify(mMockedList).add(captor.capture());

        User value = captor.getValue();

        System.out.print("value.name: " + value.getName());
    }

    private ListOfTwoElements getListMatcher() {
        return new ListOfTwoElements();
    }

    class ListOfTwoElements implements ArgumentMatcher<List> {

        @Override
        public boolean matches(List argument) {
            return argument.size() == 2;
        }

        @NonNull
        @Override
        public String toString() {
            return "list of 2 elements";
        }
    }


}
