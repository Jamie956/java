package com.jamie;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.udf.UDFType;

//表示函数返回值不重复
@UDFType(deterministic = false)
public class SnowId extends UDF {
    SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);

    public Long evaluate() {
        return idWorker.nextId();
    }
}
