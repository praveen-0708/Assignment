import com.assignment.arraylist.MyArrayList;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyArrayListTest {

    @DataProvider(name = "data")
    public Object[][] getArrayList() {
        return new Object[][]{{
                new MyArrayList<String>()
        }};
    }

    @Test(dataProvider = "data")
    public void sizeTest(List<String> list) {
        list.add("a");
        Assert.assertEquals(1, list.size());
    }

    @Test(dataProvider = "data")
    public void isEmptyTest(List<String> list) {
        Assert.assertTrue(list.isEmpty());
    }

    @Test(dataProvider = "data")
    public void addTest1(List<String> list) {
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertEquals(Arrays.asList("a", "b", "c"), list);
    }

    @Test(dataProvider = "data")
    public void getTest(List<String> list) {
        list.add("a");
        Assert.assertEquals("a", list.get(0));
    }

    @Test(dataProvider = "data")
    public void containsTest(List<String> list) {
        list.add("a");
        Assert.assertTrue(list.contains("a"));
    }

    @Test(dataProvider = "data")
    public void setTest(List<String> list) {
        list.add("a");
        list.set(0, "d");
        Assert.assertEquals("d", list.get(0));
    }

    @Test(dataProvider = "data")
    public void indexOfTest(List<String> list) {
        list.add("a");
        Assert.assertEquals(0, list.indexOf("a"));
    }

    @Test(dataProvider = "data")
    public void lastIndexOfTest(List<String> list) {
        list.add("a");
        Assert.assertEquals(0, list.lastIndexOf("a"));
    }

    @Test(dataProvider = "data")
    public void addTest2(List<String> list) {
        list.add(0, "a");
        Assert.assertEquals(Collections.singletonList("a"), list);
    }

    @Test(dataProvider = "data")
    public void removeTest(List<String> list) {
        list.add("a");
        list.remove("a");
        Assert.assertEquals(0, list.size());
    }

    @Test(dataProvider = "data")
    public void addAllTest1(List<String> list) {
        list.add("a");
        List<String> list2 = new ArrayList<>();
        list2.add("x");
        list.addAll(list2);
        Assert.assertEquals(Arrays.asList("a", "x"), list);
    }

    @Test(dataProvider = "data")
    public void addAllTest2(List<String> list) {
        list.add("a");
        List<String> list2 = new ArrayList<>();
        list2.add("x");
        list.addAll(0, list2);
        Assert.assertEquals(Arrays.asList("x", "a"), list);
    }

    @Test(dataProvider = "data")
    public void containsAllTest(List<String> list) {
        list.add("a");
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        Assert.assertTrue(list.containsAll(list2));
    }

    @Test(dataProvider = "data")
    public void removeAllTest(List<String> list) {
        list.add("a");
        list.add("b");
        list.add("c");
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list.removeAll(list2);
        Assert.assertEquals(Arrays.asList("b", "c"), list);
    }

    @Test(dataProvider = "data")
    public void retainAllTest(List<String> list) {
        list.add("a");
        list.add("b");
        list.add("c");
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list.retainAll(list2);
        Assert.assertEquals(Arrays.asList("a", "b"), list);
    }
}
