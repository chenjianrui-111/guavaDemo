package com.cjr.guava.utilities;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class JoinerTest {

    private final String targetFileName = "D:\\xiangmu\\Guava_demo\\src\\main\\resources\\guava-joiner.text";
    private final String targetFileName2 = "D:\\xiangmu\\Guava_demo\\src\\main\\resources\\guava-joiner-map.text";
    public final List<String> stringList = Arrays.asList(
            "Google","Guava","Java","Kafka"
    );

    public final List<String> stringListWithNullValue = Arrays.asList(
            "Google","Guava","Java",null
    );

    @Test
    public void testJoinOnJoin(){
        String joinResult = Joiner.on("#").join(stringList);
        assertThat(joinResult,equalTo("Google#Guava#Java#Kafka"));
    }

    @Test(expected = NullPointerException.class)
    public void testJoinOnJoinWithNullValue(){
        String joinResult = Joiner.on("#").join(stringListWithNullValue);
        assertThat(joinResult,equalTo("Google#Guava#Java"));
    }

    @Test
    public void testJoinOnJoinWithNullValueButSkip(){
        String joinResult = Joiner.on("#").skipNulls().join(stringListWithNullValue);
        assertThat(joinResult,equalTo("Google#Guava#Java"));
    }

    @Test
    public void testJoinOnJoinWithNullValueUseDefaultValue(){
        String joinResult = Joiner.on("#").useForNull("DEFAULT").join(stringListWithNullValue);
        assertThat(joinResult,equalTo("Google#Guava#Java#DEFAULT"));
    }

    @Test
    public void testJoinOnAppendToStringBuilder(){
        final  StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = Joiner.on("#").useForNull("DEFAULT").appendTo(stringBuilder, stringListWithNullValue);
        assertThat(stringBuilder1,sameInstance(stringBuilder));
        assertThat(stringBuilder1.toString(),equalTo("Google#Guava#Java#DEFAULT"));
    }

    @Test
    public void testJoinOnAppendToFileWriter(){
        try {
            FileWriter fileWriter = new FileWriter(new File(targetFileName));
            Joiner.on("#").useForNull("DEFAULT").appendTo(fileWriter, stringListWithNullValue);
            assertThat(Files.isFile().test(new File(targetFileName)),equalTo(true));
        } catch (IOException e) {
            fail("AppendToFileWriter occur error.");
        }
    }

    @Test
    public void testJoiningByStreamSkipNullValue(){
        String collect = stringListWithNullValue.stream().filter(item -> item != null && !item.isEmpty()).collect(Collectors.joining("#"));
        assertThat(collect,equalTo("Google#Guava#Java"));
    }

    @Test
    public void testJoiningByStreamUseDefaultValue(){
        String collect = stringListWithNullValue.stream().map(item -> item == null || item.isEmpty() ? "DEFAULT" : item)
                        .collect(Collectors.joining("#"));
        assertThat(collect,equalTo("Google#Guava#Java#DEFAULT"));
    }

    @Test
    public void testJoiningByStreamUseDefaultValue2(){
        String collect = stringListWithNullValue.stream().map(this::defaultValue)
                .collect(Collectors.joining("#"));
        assertThat(collect,equalTo("Google#Guava#Java#DEFAULT"));
    }

    private String defaultValue(final String item){
        return item == null || item.isEmpty() ? "DEFAULT" : item;
    }

    private final Map<String,String> stringMap = ImmutableMap.of("HELLO","JAVA","GOOGLE","SCALA");

    @Test
    public void testJoinWithMap(){
        String join = Joiner.on('#').withKeyValueSeparator("=").join(stringMap);
        assertThat(join,equalTo("HELLO=JAVA#GOOGLE=SCALA"));
    }

    @Test
    public void testJoinAppendWriterFile(){
        try {
            FileWriter fileWriter = new FileWriter(new File(targetFileName2));
            Joiner.on("#").withKeyValueSeparator("=").appendTo(fileWriter, stringMap);
            assertThat(Files.isFile().test(new File(targetFileName2)),equalTo(true));
        } catch (IOException e) {
            fail("AppendToFileWriter occur error.");
        }
    }
}
