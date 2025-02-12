FROM ubuntu:22.04

#############################################################################
# Setup base image 
#############################################################################
RUN \
  apt-get update -y && \
  apt-get install software-properties-common -y && \
  apt-get install apt-utils -y && \
  apt-get install -y openjdk-8-jdk  && \
  apt-get install -y openjdk-11-jdk && \
  apt-get update -y && \
  apt-get install -y ant && \
  apt-get install -y git \
                build-essential \
                python2 \
                python3 python3-pip python3-dev libgraphviz-dev\
                wget \
                antlr3 \
                gnupg curl unzip \
                file \
                && \
  rm -rf /var/lib/apt/lists/* 

#############################################################################
# Environment 
#############################################################################

# Set java env
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/

# Home dir
RUN mkdir --parents /home/s2120428
RUN mkdir --parents /home/s2120428/out
ENV PROJ_DIR /home/s2120428
WORKDIR ${PROJ_DIR}

RUN mkdir ${PROJ_DIR}/app

# Install objdump
RUN apt-get update -y
RUN apt-get install binutils-arm-none-eabi

# Install Z3 
RUN apt-get update -y && apt-get install -y z3

# Clone github
#RUN git clone https://github.com/vananhnt/NativeSE.git
COPY . ${PROJ_DIR}/HybridSE
ENV HYBRIDSE ${PROJ_DIR}/HybridSE/src
ENV CORANA ${HYBRIDSE}/corana

# Install mongodb
WORKDIR ${PROJ_DIR}
RUN wget -qO - https://www.mongodb.org/static/pgp/server-4.4.asc | apt-key add -
RUN curl -fsSL https://www.mongodb.org/static/pgp/server-7.0.asc | \
    gpg -o /usr/share/keyrings/mongodb-server-7.0.gpg \
   --dearmor
RUN echo "deb [ arch=amd64,arm64 signed-by=/usr/share/keyrings/mongodb-server-7.0.gpg ] https://repo.mongodb.org/apt/ubuntu jammy/mongodb-org/7.0 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-7.0.list
RUN apt-get update -y
RUN DEBIAN_FRONTEND=noninteractive apt-get install -y mongodb-org
RUN sed -i "s,\\(^[[:blank:]]*bindIp:\\) .*,\\1 0.0.0.0," /etc/mongod.conf
RUN mkdir -p /data/db 
EXPOSE 27017
RUN mongod -dbpath /var/lib/mongodb -logpath /var/log/mongodb/mongod.log --fork
#RUN /usr/bin/mongod -f /etc/mongod.conf
#ENTRYPOINT ["/usr/bin/mongod", "-f", "/etc/mongod.conf"]

#RUN update-alternatives --set java $(update-alternatives --list java | grep java-11)
#RUN update-alternatives --set javac $(update-alternatives --list javac | grep java-11)

# Install apktool
ARG APKTOOL_VERSION="2.7.0"
RUN curl -sLO https://raw.githubusercontent.com/iBotPeaches/Apktool/master/scripts/linux/apktool \
    && curl -sL -o apktool.jar https://github.com/iBotPeaches/Apktool/releases/download/v${APKTOOL_VERSION}/apktool_${APKTOOL_VERSION}.jar \
    && chmod +x apktool* \
    && mv apktool* /usr/local/bin/

# Install dex2jar
WORKDIR ${PROJ_DIR}
RUN git clone https://github.com/pxb1988/dex2jar.git
WORKDIR dex2jar
RUN ./gradlew distZip
WORKDIR dex-tools/build/distributions
RUN unzip ${PROJ_DIR}/HybridSE/lib/dex-tools-2.1-SNAPSHOT.zip

RUN git clone https://github.com/RuchirB/dex2jar-0.0.9.15.git

RUN echo "jpf-core = ${user.home}/.../path-to-jpf-core-folder/jpf-core" | tee -a "${PROJ_DIR}/.site.properties"
RUN echo "jpf-symbc = ${user.home}/.../path-to-jpf-core-folder/jpf-symbc" | tee -a "${PROJ_DIR}/.site.properties"
RUN echo "extensions={jpf-symbc}" | tee -a "${PROJ_DIR}/.site.properties"

WORKDIR ${CORANA}/lib/capstone
RUN ./make.sh
RUN ./make.sh install

RUN update-alternatives --set java $(update-alternatives --list java | grep java-8)
RUN update-alternatives --set javac $(update-alternatives --list javac | grep java-8)

WORKDIR ${HYBRIDSE}
RUN bash build.sh ${HYBRIDSE}

ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64/
RUN update-alternatives --set java $(update-alternatives --list java | grep java-11)
RUN update-alternatives --set javac $(update-alternatives --list javac | grep java-11)

WORKDIR ${PROJ_DIR}/HybridSE/preprocess
RUN pip3 install -r requirements.txt

WORKDIR ${PROJ_DIR}/HybridSE
ENTRYPOINT ["bash", "entrypoint.sh"]
#ENTRYPOINT ["python3", "preprocess/RunSample.py"]

