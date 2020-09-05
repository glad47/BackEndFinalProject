#!/usr/bin/env bash
# 定义应用组名
group_name='jugu'
# 定义应用名称
app_name='pcboninev2'
# 定义应用版本
app_version='1.0-SNAPSHOT'
# 定义应用环境
profile_active='dev'
echo '----copy jar----'
docker stop ${app_name}
echo '----stop container----'
docker rm ${app_name}
echo '----rm container----'
docker rmi ${group_name}/${app_name}:${app_version}
echo '----rm image----'
# 打包编译docker镜像
docker build -t ${group_name}/${app_name}:${app_version} .
echo '----build image----'
docker run -p 8877:8877 --name ${app_name} \
--link mysql:db \
--link redis:redis \
--link minio:minio \
-e 'spring.profiles.active'=${profile_active} \
-e TZ="Asia/Shanghai" \
-v /etc/localtime:/etc/localtime \
-v /mydata/app/${app_name}/logs:/var/logs \
-d ${group_name}/${app_name}:${app_version}
echo '----start container----'