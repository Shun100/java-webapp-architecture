FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app
COPY . .

# Gradle Wrapperに実行権限を付与する
RUN chmod +x ./gradlew

# WARのビルド
RUN ./gradlew clean build --no-daemon

# PayaraにWARをデプロイ
FROM payara/server-full:latest

# Payaraの自動デプロイディレクトリ
ENV DEPLOY_DIR /opt/payara/deployments

# WARをPayaraのデプロイ先へコピー
COPY --from=builder /app/build/libs/java-webapp-architecture.war ${DEPLOY_DIR}