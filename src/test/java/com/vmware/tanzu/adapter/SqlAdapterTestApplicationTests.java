package com.vmware.tanzu.adapter;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.tool.method.MethodToolCallback;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SqlAdapterTestApplicationTests {

	@Autowired
	private ChatClient.Builder chatClientBuilder;

	@Autowired
	private QueryExecutorTool queryExecutorTool;

	@Test
	void contextLoads() {
		assertThat(chatClientBuilder).isNotNull();
	}

	@Test
	void canExecuteSomeSql() {
		assertThat(chatClientBuilder).isNotNull();

		ChatClient client = chatClientBuilder
				.defaultOptions(
						ToolCallingChatOptions.
								builder().
								model("gpt-4o-mini").
								build())
				.build();

		String response = client
				.prompt("list the tables in my local postgres database?")
				.tools(queryExecutorTool)
				.call()
				.content();
		assertThat(response).isNotNull();

		System.out.println("\n\n");
		System.out.println(response);
		System.out.println("\n\n");

		response = client
				.prompt("what does the audit table look like in my postgres database?")
				.tools(queryExecutorTool)
				.call()
				.content();
		assertThat(response).isNotNull();

		System.out.println("\n\n");
		System.out.println(response);
		System.out.println("\n\n");
	}
}
