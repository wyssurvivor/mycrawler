package com.wys.anotherdata;

public class DataOperateFactory {
	public static AbstractDataOperate getInstance(DataType dtype){
			return dtype.getDataOperate();
	}
}
