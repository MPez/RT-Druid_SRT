#!/bin/bash

###################################
# Custom script to build RT-Druid #
###################################

TEMP_FOLDER=/tmp
EE_BASE_LOCATION=/home/davide/Documents/Erika/ee/ee
DESTINATION=/home/davide/Documents/Erika/RT-Druid_OUT
export ECLIPSE_HOME=/home/davide/Documents/Erika/eclipse
export JAVA_HOME=/etc/alternatives/java_sdk_openjdk

./start_ant_builder.sh -Dbuild.compiler=javac1.7 -Denable_oil_core_features=true -Denable_oil_ee_features=true -Denable_sched_analysis_features=true -Dee.base.location=$EE_BASE_LOCATION -Dtemp.folder=$TEMP_FOLDER -Ddestination=$DESTINATION -DoutputFile=RT_Druid zip.distribution
