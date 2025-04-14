#!/bin/bash

export PROJ_DIR=$1
#echo "$2"
export CORANA=${PROJ_DIR}/corana
echo "RUN SE TOOLS"
cd ${PROJ_DIR}
echo ${PROJ_DIR}
#echo "BUILDING JPF-SYMBC"
#cd ${PROJ_DIR}/jpf-core/ && ant clean build && cd ..
#cd ${PROJ_DIR}/jpf-symbc/ && ant clean build && cd ..
#cd ${PROJ_DIR}/jpf-nhandler/ && ant clean build && cd ..
#sudo mongod -dbpath /var/lib/mongo -logpath /var/log/mongodb/mongod.log --fork

/usr/lib/jvm/java-8-openjdk-amd64/bin/java \
-Xss16m -Xmx20240m -Dfile.encoding=UTF-8 \
-classpath ${PROJ_DIR}/jpf-core/build/main:\
${PROJ_DIR}/jpf-core/build/peers:${PROJ_DIR}/jpf-core/build/classes:\
${PROJ_DIR}/jpf-core/build/annotations:${PROJ_DIR}/jpf-core/build/examples:\
${PROJ_DIR}/jpf-core/build/tests:${PROJ_DIR}/jpf-symbc/build/main:\
${PROJ_DIR}/jpf-symbc/build/peers:${PROJ_DIR}/jpf-symbc/build/classes:\
${PROJ_DIR}/jpf-symbc/lib/*:${PROJ_DIR}/jpf-symbc/lib/corana.jar:\
${PROJ_DIR}/jpf-core/build main.corana.external.connector.SetupJNI $2 $3 
