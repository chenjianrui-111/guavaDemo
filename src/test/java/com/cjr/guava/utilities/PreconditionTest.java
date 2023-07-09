package com.cjr.guava.utilities;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class PreconditionTest {

    @Test(expected = NullPointerException.class)
    public void testCheckNotNull(){
        checkNotNull(null);
    }

    @Test
    public void testCheckNotNullWithMessage(){
        try {
            CheckNotNullWithMessage(null);
        }catch (NullPointerException e){
            assertThat(e,is(NullPointerException.class));
        }

    }

    @Test
    public void testCheckNotNullWithFormatMessage(){
        try {
            CheckNotNullWithFormatMessage(null);
        }catch (NullPointerException e){
            assertThat(e,is(NullPointerException.class));
        }
    }

    @Test
    public void testCheckArguments(){
        final String type = "A";
        try {
            Preconditions.checkArgument(type.equals("B"));
        }catch (IllegalArgumentException e){
            assertThat(e,is(IllegalArgumentException.class));
        }
    }

    @Test
    public void testCheckState(){
        try {
            // FIXME: 2023/7/8
            final String type = "A";
            Preconditions.checkState(type.equals("B"),"The state is IllegalState");
            fail("should not process to here.");
        }catch (IllegalStateException e){
            assertThat(e,is(IllegalStateException.class));
        }
    }

    @Test
    public void testCheckIndex(){
        try {
            List<String> immutableList = ImmutableList.of();
            Preconditions.checkElementIndex(10,immutableList.size());
        }catch (IndexOutOfBoundsException e){
            assertThat(e,is(IndexOutOfBoundsException.class));
        }
    }

    //Java8中判断是否为空
    @Test(expected = NullPointerException.class)
    public void testObjects(){
        Objects.requireNonNull(null);
    }

    @Test
    public void testAssertWithMessage(){
        try {
            List<String> list = null;
            assert list != null : "The list should not be null";
        }catch (AssertionError error){
            assertThat(error,equalTo("The list should not be null"));
        }
    }
    private void checkNotNull(final List<String> list){
        Preconditions.checkNotNull(list);
    }

    private void CheckNotNullWithMessage(final List<String> list){
        Preconditions.checkNotNull(list,"The Message Should be Not null");
    }

    private void CheckNotNullWithFormatMessage(final List<String> list){
        Preconditions.checkNotNull(list,"The Message Should be Not null and the size must be %s",2);
    }

}
