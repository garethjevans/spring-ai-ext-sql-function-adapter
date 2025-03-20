package com.vmware.tanzu.adapter;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;
import com.vmware.tanzu.ai.queryadapter.QueryExecutor;
import com.vmware.tanzu.ai.queryadapter.impl.DefaultQueryExecutor;

@Configuration
public class SqlAdapterConfiguration {

    @Bean
    public ToolCallbackProvider weatherTools(QueryExecutorTool queryExecutorTool) {
        return MethodToolCallbackProvider.builder().toolObjects(queryExecutorTool).build();
    }

    @Bean
    public QueryExecutorTool sqlAdapter(QueryExecutor queryExecutor) {
        return new QueryExecutorTool(queryExecutor);
    }

    @Bean
    public QueryExecutor queryExecutor(JdbcClient jdbcClient) {
        return new DefaultQueryExecutor(jdbcClient);
    }

    // needed when calling via a proxy
    @Bean
    public RestClientCustomizer restClientCustomizer() {
        return new RestClientHttpCustomizer();
    }
}
