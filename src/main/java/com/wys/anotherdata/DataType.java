package com.wys.anotherdata;

public enum DataType {
	MySQL{AbstractDataOperate getDataOperate(){return new MySQLDataOperate();}},
	Oracle{AbstractDataOperate getDataOperate(){return new OracleDataOperate();}};
	
	abstract AbstractDataOperate getDataOperate();
}
