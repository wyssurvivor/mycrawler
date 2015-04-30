package com.wys.anotherdata;

public class DataOperateFactory {
	public static DataOperate getInstance(int dtype){
			return DataType.valueOf(String.valueOf(dtype)).getDataOperate();
	}
}
