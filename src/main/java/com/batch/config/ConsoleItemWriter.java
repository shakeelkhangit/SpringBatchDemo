package com.batch.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
 
public class ConsoleItemWriter<String> implements ItemWriter<String> { 
    @Override
    public void write(List<? extends String> items) throws Exception { 
        for (String item : items) { 
            System.out.println("xxxxxxxxxxxx===="+item); 
        } 
    } 
}
