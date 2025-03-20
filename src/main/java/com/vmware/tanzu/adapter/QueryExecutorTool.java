package com.vmware.tanzu.adapter;

import org.springframework.ai.tool.annotation.Tool;
import com.vmware.tanzu.ai.queryadapter.QueryExecutor;
import org.springframework.ai.tool.annotation.ToolParam;

import java.util.List;
import java.util.Map;

public class QueryExecutorTool {

    private final QueryExecutor queryExecutor;

    public QueryExecutorTool(QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    @Tool(
            description = "will execute a query against the database to return results")
    public List<Map<String, Object>> execute(@ToolParam(description = "the query to be passed to the database") String query) {
        try {
            System.out.println("executing query: " + query);
            List<Map<String, Object>> maps = queryExecutor.queryDataSource(query);
            System.out.println("got = " + maps);
            return maps;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
