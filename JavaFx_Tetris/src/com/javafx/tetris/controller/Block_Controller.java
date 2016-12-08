package com.javafx.tetris.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.javafx.tetris.block.Block;
import com.javafx.tetris.block.JBlock;
import com.javafx.tetris.block.LBlock;
import com.javafx.tetris.block.OBlock;

public class Block_Controller {

	ArrayList<Block> block_list;
	Queue<Block> next_list;
	Block currBlock;
	
	public Block_Controller() {
		block_list = new ArrayList<>();
		next_list = new LinkedList<>();
	}
	
	public void blockCreate(){
		block_list.add(new OBlock());
		//block_list.add(new IBlock());
		//block_list.add(new SBlock());
		//block_list.add(new ZBlock());
		block_list.add(new LBlock());
		block_list.add(new JBlock());
		//block_list.add(new TBlock());
		next_list.add(block_list.get((int)(Math.random()*block_list.size())));
		next_list.add(block_list.get((int)(Math.random()*block_list.size())));
		
		currBlock = getBlock();
	}
	
	public Block getBlock(){				
		 if (next_list.size() <= 1) {														//next_list가 1개 이하로 떨어지면
			 next_list.add(block_list.get((int)(Math.random()*block_list.size())));			//자동으로 next_list에 block을 하나 더 추가
	        }
	        return next_list.poll();		//다음 Block를 하나 꺼내어 리턴(poll은 값을 제거)
	}
	
	public Block getNextBlock(){
		 return next_list.peek();			//다음 Block를 하나 꺼내어 리턴(peek은 값 제거X)
	}
	
	public Block BlockDown(){
		currBlock.offset.moveYX(1, 0);
		return currBlock;
	}
	
	//랜덤블럭생성
	//블럭카피
	//블럭이동
	//블럭충돌확인
}
