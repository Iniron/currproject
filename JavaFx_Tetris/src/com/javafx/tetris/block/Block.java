package com.javafx.tetris.block;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Block {
	
	public final int PointCnt = 4;				//����Ʈ�� ����
	
	public B_Point offset;				//���� ���� ��ġ
	public ArrayList<B_Point[]> shape_list;		//�� ������ ���
	public B_Point[] shape;				//���� ���
	public Color color;					//���� ����
	public int angle;					//����
	//public int point_size;
	
	public Block() {
		offset = new B_Point(0, 0);
		shape_list = new ArrayList<>();
		shape = new B_Point[PointCnt];
	}
	
	public void setAngle(int angle){
		this.angle = angle;//���� ����
		//shape = shape_list.get(angle);		//������ ���� ��纯��
		//shape = shape_list.get(angle).clone();
		System.arraycopy(shape_list.get(angle), 0, shape, 0, PointCnt);
	}
	
	public B_Point[] getCurrPoint(){
		B_Point[] temp = new B_Point[PointCnt];
		for (int i=0; i<shape.length; i++){                  
			int x = shape[i].getX() + offset.getX();  
			int y = shape[i].getY() + offset.getY();
			temp[i] = new B_Point(y,x);
		}
		return temp;
	}
	
	public B_Point[] getNextShape(){
		int nextangle = angle;
		if(nextangle==shape_list.size()-1) nextangle=0;
		else nextangle++;
		
		B_Point[] temp = new B_Point[PointCnt];
		for (int i=0; i<shape_list.get(nextangle).length; i++){                  
			int x = shape_list.get(nextangle)[i].getX() + offset.getX();  
			int y = shape_list.get(nextangle)[i].getY() + offset.getY();
			temp[i] = new B_Point(y,x);
		}
		return temp;
	}
}
