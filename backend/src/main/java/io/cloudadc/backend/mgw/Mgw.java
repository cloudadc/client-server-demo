package io.cloudadc.backend.mgw;

import java.io.Serializable;

public class Mgw implements Serializable {

	private static final long serialVersionUID = -7163796995267278959L;
	
	private String item_1, item_2, item_3, item_4, item_5;
	
	public Mgw() {
		
	}
	
	public Mgw(String item_1) {
		super();
		this.item_1 = item_1;
	}
	
	public Mgw(String item_1, String item_2) {
		super();
		this.item_1 = item_1;
		this.item_2 = item_2;
	}
	
	public Mgw(String item_1, String item_2, String item_3) {
		super();
		this.item_1 = item_1;
		this.item_2 = item_2;
		this.item_3 = item_3;
	}
	
	public Mgw(String item_1, String item_2, String item_3, String item_4) {
		super();
		this.item_1 = item_1;
		this.item_2 = item_2;
		this.item_3 = item_3;
		this.item_4 = item_4;
	}

	public Mgw(String item_1, String item_2, String item_3, String item_4, String item_5) {
		super();
		this.item_1 = item_1;
		this.item_2 = item_2;
		this.item_3 = item_3;
		this.item_4 = item_4;
		this.item_5 = item_5;
	}

	public String getItem_1() {
		return item_1;
	}

	public void setItem_1(String item_1) {
		this.item_1 = item_1;
	}

	public String getItem_2() {
		return item_2;
	}

	public void setItem_2(String item_2) {
		this.item_2 = item_2;
	}

	public String getItem_3() {
		return item_3;
	}

	public void setItem_3(String item_3) {
		this.item_3 = item_3;
	}

	public String getItem_4() {
		return item_4;
	}

	public void setItem_4(String item_4) {
		this.item_4 = item_4;
	}

	public String getItem_5() {
		return item_5;
	}

	public void setItem_5(String item_5) {
		this.item_5 = item_5;
	}

	@Override
	public String toString() {
		return "{item_1:" + item_1 + ", item_2:" + item_2 + ", item_3:" + item_3 + ", item_4:" + item_4 + ", item_5:" + item_5 + "}";
	}

}
