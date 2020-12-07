package com.sibedge.ergo.api;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describes a shared test configuration for integration API tests.
 *
 * @see com.sibedge.ergo.api.PersonSearchEndpoint
 */
@Transactional
@SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql(scripts = "/assets/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseApiTest {
}
