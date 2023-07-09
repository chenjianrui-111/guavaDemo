package com.cjr.guava.functional;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Preconditions;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class FunctionalExample {
    public static void main(String[] args) {
        Function<String,Integer> function = new Function<String,Integer>() {
            @NullableDecl
            @Override
            public Integer apply(@NullableDecl String input) {
                Preconditions.checkNotNull(input,"The Stream should not be null");
                return input.length();
            }
        };
        System.out.println(function.apply("hello"));

        process("Hello",new Handler.LengthDoubleHandler());

        Function<String, Long> compose = Functions.compose(new Function<Integer, Long>() {
            @NullableDecl
            @Override
            public Long apply(@NullableDecl Integer input) {
                return input * 1L;
            }
        }, new Function<String, Integer>() {
            @NullableDecl
            @Override
            public Integer apply(@NullableDecl String input) {
                return input.length();
            }
        });
        System.out.println(compose.apply("Hello"));
    }

    interface Handler<IN,OUT> {
        OUT handle(IN input);

        class LengthDoubleHandler implements Handler<String, Integer> {

            @Override
            public Integer handle(String input) {
                return input.length() * 2;
            }
        }
    }

        static void process(String text, Handler<String, Integer> handler){
            System.out.println(handler.handle(text));
        }

}
