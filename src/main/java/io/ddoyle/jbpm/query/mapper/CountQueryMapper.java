package io.ddoyle.jbpm.query.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dashbuilder.dataset.DataSet;
import org.jbpm.kie.services.impl.query.mapper.AbstractQueryMapper;
import org.jbpm.services.api.query.QueryResultMapper;

/**
 * Simple <code>QueryMapper</code> that maps a column with the name "COUNT" to a {@link List} of {@link CountResultDesc}.
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
public class CountQueryMapper extends AbstractQueryMapper<CountResultDesc> implements QueryResultMapper<List<CountResultDesc>> {

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String QUERY_MAPPER_NAME = "Count";
	
	public static final String COLUMN_COUNT = "COUNT";
	
	/**
     * Dedicated for ServiceLoader to create instance, use <code>get()</code> method instead 
     */
	public CountQueryMapper() {
    }
    
	public static CountQueryMapper get() {
        return new CountQueryMapper();
    }
	
	@Override
	public String getName() {
		return QUERY_MAPPER_NAME;
	}
	
	@Override
	public List<CountResultDesc> map(Object result) {
		if (result instanceof DataSet) {
            DataSet dataSetResult = (DataSet) result;
            List<CountResultDesc> mappedResult = new ArrayList<>();
            
            if (dataSetResult != null) {
                for (int i = 0; i < dataSetResult.getRowCount(); i++) {
                    mappedResult.add(buildInstance(dataSetResult, i));
                }
            }
            return mappedResult;
        }
        throw new IllegalArgumentException("Unsupported result for mapping " + result);
	}

	@Override
	public Class<?> getType() {
		return Long.class;
	}

	@Override
	public QueryResultMapper<List<CountResultDesc>> forColumnMapping(Map<String, String> columnMapping) {
		return new CountQueryMapper();
	}

	@Override
	protected CountResultDesc buildInstance(DataSet dataSetResult, int index) {
		return new CountResultDesc(index, getColumnLongValue(dataSetResult, COLUMN_COUNT, index));
	}

}
