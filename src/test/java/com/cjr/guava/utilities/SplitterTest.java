package com.cjr.guava.utilities;

import com.google.common.base.Splitter;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class SplitterTest {

    @Test
    public void testSplitOnSplit(){
        List<String> splitList = Splitter.on("|").splitToList("HELLO|JAVA");
        assertThat(splitList, IsNull.notNullValue());
        assertThat(splitList.size(),equalTo(2));
        assertThat(splitList.get(0),equalTo("HELLO"));
    }

    @Test
    public void testSplitOnSplitOmitEmpty(){
        List<String> splitList = Splitter.on("|").splitToList("HELLO|JAVA|||");
        assertThat(splitList, IsNull.notNullValue());
        assertThat(splitList.size(),equalTo(5));
        List<String> splitList2 = Splitter.on("|").omitEmptyStrings().splitToList("HELLO|JAVA|||");
        assertThat(splitList2, IsNull.notNullValue());
        assertThat(splitList2.size(),equalTo(2));
    }

    @Test
    public void testSplitOnSplitOmitEmptyTrimResult(){
        List<String> splitList = Splitter.on("|").trimResults().omitEmptyStrings().splitToList("  HELLO | JAVA|||");
        assertThat(splitList.size(),equalTo(2));
        assertThat(splitList.get(0),equalTo("HELLO"));
    }

    /**
     * aaaabbbbcccc
     */
    @Test
    public void testSplitFixLength(){
        List<String> splitList = Splitter.fixedLength(4).splitToList("aaaabbbb");
        assertThat(splitList.size(),equalTo(2));
        assertThat(splitList.get(0),equalTo("aaaa"));
    }

    @Test
    public void testSplitOnLimit(){
        List<String> splitList = Splitter.on("#").limit(3).splitToList("JAVA#GUAVA#KAFKA#SCALA#PYTHON");
        assertThat(splitList.size(),equalTo(3));
        assertThat(splitList.get(0),equalTo("JAVA"));
        assertThat(splitList.get(1),equalTo("GUAVA"));
        assertThat(splitList.get(2),equalTo("KAFKA#SCALA#PYTHON"));
    }

    @Test
    public void testSplitOnPatternString(){
        List<String> splitList = Splitter.onPattern("\\|").trimResults().omitEmptyStrings().splitToList("  HELLO |WORD");
        assertThat(splitList.size(),equalTo(2));
        assertThat(splitList.get(0),equalTo("HELLO"));
    }

    @Test
    public void testSplitOnPattern(){
        List<String> splitList = Splitter.on(Pattern.compile("\\|")).trimResults().omitEmptyStrings().splitToList("  HELLO |WORD");
        assertThat(splitList.size(),equalTo(2));
        assertThat(splitList.get(0),equalTo("HELLO"));
    }

    @Test
    public void testSplitOnSplitToMap(){
        Map<String,String> splitMap = Splitter.on(Pattern.compile("\\|")).trimResults().omitEmptyStrings().withKeyValueSeparator("=").split("  HELLO=hello |WORD=word|||");
        assertThat(splitMap.size(),equalTo(2));
        assertThat(splitMap.get("HELLO"),equalTo("hello"));
    }
}
