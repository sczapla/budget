#!/bin/bash

set -e

NAME='alioraty'

if [ -z "$1" ]
then
    VERSION=`grep -A 1 alioraty pom.xml | sed -nr 's|.*<version>(.*)</version>.*|\1|p'`
    REPO='docker-registry.service.cashbill:5000/cashbill/'${NAME}
    mvn clean package # test only for production builds
    PUSH='yes'
else
    VERSION=$1
    REPO=${NAME}
    PUSH='no'
fi


echo -e "\033[0;32m*** Building docker image\033[0m"
docker build -t ${REPO}:${VERSION} .


if [ $PUSH = "yes" ]; then
echo -e "\033[0;32m*** Creating provision.d script\033[0m"
mkdir -p provision.d
cat << EOS > provision.d/$NAME.sh
#!/bin/sh

VERSION=${VERSION}
NAME=${NAME}

docker run -d --name \${NAME} \\
    --restart=on-failure:3 \\
    -p 8270:8080 \\
    -e JAVA_OPTS="-Xms128m -Xmx128m" \\
    -v /mnt/storage/conf/alioraty:/alioraty/conf \\
    -v /etc/localtime:/etc/localtime:ro -v /etc/timezone:/etc/timezone:ro \\
    --log-opt tag="\${NAME}" --log-driver=syslog \\
    -m 512m \\
    ${REPO}:\${VERSION}

EOS

chmod +x provision.d/${NAME}.sh

echo -e "\033[0;32m*** Remember to push image to repository:\033[0m"
echo -e "\n\033[0;33mdocker push ${REPO}:${VERSION}\033[0m\n"

fi;

