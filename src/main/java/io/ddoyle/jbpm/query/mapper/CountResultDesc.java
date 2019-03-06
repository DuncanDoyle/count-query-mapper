package io.ddoyle.jbpm.query.mapper;

/**
 * Descriptor class for <code>counts</code> in SQL queries.
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
public class CountResultDesc {
	
	private final int index;
	
	private final Long count;
	
	public CountResultDesc(int index, Long count) {
		this.index = index;
		this.count = count;
	}
	
	public int getIndex() {
		return index;
	}

	public Long getCount() {
		return count;
	}

}
