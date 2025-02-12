#!/bin/bash

export PROJ_DIR=$1
cd ${PROJ_DIR}
#echo "BUILDING JPF-SYMBC"
cd ${PROJ_DIR}/jpf-core/ && ant clean build && cd ..
cd ${PROJ_DIR}/jpf-symbc/ && ant clean build && cd ..
cd ${PROJ_DIR}/jpf-nhandler/ && ant clean build && cd ..

#sudo mongod -dbpath /var/lib/mongo -logpath /var/log/mongodb/mongod.log --fork

