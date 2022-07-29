package kr.human.camping.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailMapVO {
	private int idx;
	private int areacode;
	private String detailmap;
	private String deteailmapimage;
	private String coordinate;
	private String col1;
	private int col2;
}
