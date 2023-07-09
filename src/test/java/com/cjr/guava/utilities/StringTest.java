package com.cjr.guava.utilities;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

public class StringTest {

    @Test
    public void testStringMethod(){
        assertThat(Strings.emptyToNull(""),nullValue());
        assertThat(Strings.nullToEmpty(null),equalTo(""));
        assertThat(Strings.nullToEmpty("hello"),equalTo("hello"));
        assertThat(Strings.repeat("hello",2),equalTo("hellohello"));
        assertThat(Strings.commonPrefix("hello","hit"),equalTo("h"));
        assertThat(Strings.commonSuffix("hello","hit"),equalTo(""));
        assertThat(Strings.isNullOrEmpty(""),equalTo(true));
        assertThat(Strings.isNullOrEmpty(null),equalTo(true));
        assertThat(Strings.padStart("hello",6,'o'),equalTo("ohello"));
        assertThat(Strings.padEnd("hello",6,'o'),equalTo("helloo"));
    }

    @Test
    public void testCharset(){
        Charset charset = Charset.forName("UTF-8");
        assertThat(Charsets.UTF_8,equalTo(charset));
    }

    @Test
    public void testCharMatcher(){
        assertThat(CharMatcher.javaDigit().matches('5'),equalTo(true));
        assertThat(CharMatcher.javaDigit().matches('h'),equalTo(false));
        assertThat(CharMatcher.breakingWhitespace().collapseFrom("  java guava  ",'*'),equalTo("*java*guava*"));
        assertThat(CharMatcher.is('A').countIn("ALEX IS THE BEST TEACHER"),equalTo(2));
        assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).removeFrom("hello 234 world"),equalTo("helloworld"));
        assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).retainFrom("hello 234 world"),equalTo("234"));
    }
}
