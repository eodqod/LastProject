package kr.human.camping.vo;

import lombok.Data;

@Data
public class RoomVO {
	private int roomidx;
	private int idx;
	private String roomname;
	private int minpeople;
	private int maxpeople;
	private int price;
	private int r_check;
	private String content;
}
