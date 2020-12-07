--
-- Removes all data that can be generated during a test execution.
-- Basically, it is useful for web layer testing when traditional test transactions and rollbacks don't work.
--
TRUNCATE "person";
