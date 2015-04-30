package com.wys.anotherdata;

public enum DataType {
	MySQL{DataOperate getDataOperate(){return new MySQLDataOperate();}},
	Oracle{DataOperate getDataOperate(){return new OracleDataOperate();}};
	
	abstract DataOperate getDataOperate();
}
