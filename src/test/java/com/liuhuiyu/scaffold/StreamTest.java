package com.liuhuiyu.scaffold;

import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-09-08 8:59
 */
@Log4j2
public class StreamTest extends TestCase {
    /**
     * filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串：
     */
    public void test01() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "bc", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        filtered.forEach(log::debug);
    }

    /**
     * limit 方法用于获取指定数量的流。 以下代码片段使用 limit 方法打印出 10 条数据：
     */
    public void test02() {
        Random random = new Random();
        random.ints().limit(10).forEach(log::debug);
    }

    /**
     * sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：
     */
    public void test03() {
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(log::debug);
    }

    /**
     * 并行（parallel）程序
     */
    public void test04() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 获取空字符串的数量
        long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        log.debug("空白数量：" + count);
    }

    /**
     * Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
     */
    public void test05(){
        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        log.debug("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        log.debug("合并字符串: " + mergedString);
    }
}
