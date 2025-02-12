#!/bin/bash

mongod -dbpath /var/lib/mongodb -logpath /var/log/mongodb/mongod.log --fork
python3 preprocess/RunSample.py $1 -n 1
