#!/usr/bin/env bash

exec java $JAVA_OPTS -jar "${APP_ROOT}/app.jar" "$@"
