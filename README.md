AWS lambda上で動く、Kotlin製SlackBotです。

## Zipファイル作成

```
./gradlew build
```

## AWS CLIコマンド

### 作成

```
aws lambda create-function --region ap-northeast-1 --function-name <ファンクション名> --zip-file fileb://build/distributions/aws-lambda-kotlin.zip --role arn:aws:iam::<AWSアカウントID>:role/lambda_basic_execution --handler net.nocono.Main::handler --runtime java8 --timeout 15 --memory-size 512
```

### 更新

```
aws lambda update-function-code --region ap-northeast-1 --function-name <ファンクション名> --zip-file fileb://build/distributions/aws-lambda-kotlin.zip
```

## 注意点

SlackのOutbound Webhookから来るデータは `application/x-www-form-urlencoded` のPOSTであるため、 API Gateway でJSONにマッピングする必要があります。

[AWS API GatewayでContent-Type:application/x-www-form-urlencoded のPOSTデータを受け取り JSONに変換する - Qiita](http://qiita.com/durosasaki/items/83af014aa85a0448770e)