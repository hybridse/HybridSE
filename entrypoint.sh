#!/bin/bash

mongod -dbpath /var/lib/mongodb -logpath /var/log/mongodb/mongod.log --fork
python3 preprocess/Main.py $1 -n 1
