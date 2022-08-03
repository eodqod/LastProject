package kr.human.camping.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MapVO {
	private int areacode;
	private String coordinate;
	private String mapimage;
	private String col1;
	private int col2;
}
