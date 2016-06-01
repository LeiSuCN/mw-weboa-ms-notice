package com.mailworld.weboa.ms.notice.domain;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class LongSqlTimestampTypeHandler extends BaseTypeHandler<Long> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType)
			throws SQLException {
	}

	@Override
	public Long getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return changeTimestamp2Long(rs.getTimestamp(columnName));
	}

	@Override
	public Long getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return changeTimestamp2Long(rs.getTimestamp(columnIndex));
	}

	@Override
	public Long getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return changeTimestamp2Long(cs.getTimestamp(columnIndex));
	}
	
	private long changeTimestamp2Long(Timestamp timestamp){
		return timestamp == null ? 0l : timestamp.getTime();
	}

}
