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
		 if (next_list.size() <= 1) {														//next_list�� 1�� ���Ϸ� ��������
			 next_list.add(block_list.get((int)(Math.random()*block_list.size())));			//�ڵ����� next_list�� block�� �ϳ� �� �߰�
	        }
	        return next_list.poll();		//���� Block�� �ϳ� ������ ����(poll�� ���� ����)
	}
	
	public Block getNextBlock(){
		 return next_list.peek();			//���� Block�� �ϳ� ������ ����(peek�� �� ����X)
	}
	
	public Block BlockDown(){
		currBlock.offset.moveYX(1, 0);
		return currBlock;
	}
	
	//����������
	//��ī��
	//���̵�
	//���浹Ȯ��
}
