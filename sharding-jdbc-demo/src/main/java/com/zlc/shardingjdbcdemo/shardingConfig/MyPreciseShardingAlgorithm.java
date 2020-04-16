package com.zlc.shardingjdbcdemo.shardingConfig;

import com.zlc.shardingjdbcdemo.util.DateUtils;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.Date;

/**
 * @author : ZLC
 * @create : 2020-04-08 17:07
 * @desc : 自定义分片算法
 **/
public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Date> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
        //对于表的分片collection存放的是所有的表的列表，这里代表
        //设置分片的sharding-column的值
        Date dateValue = preciseShardingValue.getValue();
        String dateValueSuffix = DateUtils.getDateString(dateValue,DateUtils.YYYYMM);
        //分库时配置的sharding-column
        String columnName = preciseShardingValue.getColumnName();
        //需要分库的逻辑表
        String table = preciseShardingValue.getLogicTableName();
        if(dateValue == null){
            throw new UnsupportedOperationException("preciseShardingValue is null");
        }
        for(String tableName: collection){
            if(table.endsWith(dateValueSuffix)){
                //这里返回去的就是要查询的表
                return tableName;
            }
        }
        return null;
    }
}
