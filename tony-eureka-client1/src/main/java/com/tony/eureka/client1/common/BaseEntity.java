package com.tony.eureka.client1.common;

import java.io.Serializable;

/**
 * @author tony
 * @describe BaseEntity
 * @date 2019-10-17
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 5437446052305782003L;

    protected String sortColumn;

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn == null ? null : sortColumn.trim();
    }
}
