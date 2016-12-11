package com.javafx.tetris.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.javafx.tetris.block.Block;
import com.javafx.tetris.block.IBlock;
import com.javafx.tetris.block.JBlock;
import com.javafx.tetris.block.LBlock;
import com.javafx.tetris.block.OBlock;
import com.javafx.tetris.block.SBlock;
import com.javafx.tetris.block.TBlock;
import com.javafx.tetris.block.ZBlock;

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
		block_list.add(new IBlock());
		block_list.add(new SBlock());
		block_list.add(new ZBlock());
		block_list.add(new LBlock());
		block_list.add(new JBlock());
		block_list.add(new TBlock());
		next_list.add(block_list.get((int)(Math.random()*block_list.size())));
		next_list.add(block_list.get((int)(Math.random()*block_list.size())));
	}
	
	public void getBlock(){				
		 if (next_list.size() <= 1) {														//next_list�� 1�� ���Ϸ� ��������
			 next_list.add(block_list.get((int)(Math.random()*block_list.size())));			//�ڵ����� next_list�� block�� �ϳ� �� �߰�
	        }
		 currBlock = next_list.poll();
	}
	
	public Block getNextBlock(){
		 return next_list.peek();															//���� Block�� �ϳ� ������ ����(peek�� �� ����X)
	}
	
	public void moveDown(){
		currBlock.offset.moveYX(1, 0);
	}
	
	public void moveLeft(){
		currBlock.offset.moveYX(0, -1);
	}
	
	public void moveRight(){
		currBlock.offset.moveYX(0, 1);
	}
	
	public void rotate(){
		if(currBlock.shape_list.size() == 1) return;
		if(currBlock.angle == currBlock.shape_list.size()-1){
			currBlock.setAngle(0);
		}else{
			currBlock.setAngle(currBlock.angle+1);
		}
	}
}
