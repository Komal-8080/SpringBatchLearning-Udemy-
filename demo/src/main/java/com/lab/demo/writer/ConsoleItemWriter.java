package com.lab.demo.writer;

import java.util.List;

import org.springframework.batch.item.support.AbstractItemStreamItemWriter;

public class ConsoleItemWriter extends AbstractItemStreamItemWriter{

	@Override
	public void write(List items) throws Exception {
		// TODO Auto-generated method stub
		items.stream().forEach(System.out::println);
		System.out.println("********Writting Each Chunk*********");
	}

}
