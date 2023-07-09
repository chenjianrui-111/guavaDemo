package com.cjr.guava.io;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.io.Files;
import org.junit.After;
import org.junit.Test;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class FilesTest {

    private final String SOURCE_FILE ="D:\\xiangmu\\Guava_demo\\src\\test\\resources\\io\\source.txt";
    private final String TARGET_FILE ="D:\\xiangmu\\Guava_demo\\src\\test\\resources\\io\\target.txt";

    @Test
    public void testCopyFileWithGuava() throws IOException {
        File tatgetName = new File(TARGET_FILE);
        Files.copy(new File(SOURCE_FILE),tatgetName);
        assertThat(tatgetName.exists(),equalTo(true));
    }

    @Test
    public void testCopyFileWithJDKNio2() throws IOException {
        java.nio.file.Files.copy(
                Paths.get("D:\\xiangmu\\Guava_demo\\src\\test\\resources","io","source.txt"),
                Paths.get("D:\\xiangmu\\Guava_demo\\src\\test\\resources","io","target.txt"),
                StandardCopyOption.REPLACE_EXISTING
        );
    }

    @Test
    public void testMoveFile() throws IOException{
        try {
            Files.move(new File(SOURCE_FILE),new File(TARGET_FILE));
            assertThat(new File(TARGET_FILE).exists(),equalTo(true));
            assertThat(new File(SOURCE_FILE).exists(),equalTo(false));
        }finally {
            Files.move(new File(TARGET_FILE),new File(SOURCE_FILE));
        }
    }

    @Test
    public void testToString() throws IOException{
        final String expected = "today we will share guava";
        List<String> list = Files.readLines(new File(SOURCE_FILE), Charsets.UTF_8);
        String result =Joiner.on("").join(list);
        assertThat(result,equalTo(expected));
    }
    @After
    public void deleteFile(){
        File targetFile =new File(TARGET_FILE);
        if (targetFile.exists()){
            targetFile.delete();
        }
    }
}
