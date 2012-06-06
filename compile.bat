@rem ************************************************************
@rem
@rem file:  compile.bat
@rem desc:  Example batch file to compile a 'hello' CoreDX DDS
@rem        Java application.
@rem
@rem ************************************************************
@rem
@rem Coypright(C) 2006-2011 Twin Oaks Computing, Inc
@rem All rights reserved.   Castle Rock, CO 80108
@rem
@rem *************************************************************
@rem
@rem This file is provided by Twin Oaks Computing, Inc
@rem as an example. It is provided in the hope that it will be 
@rem useful but WITHOUT ANY WARRANTY; without even the implied 
@rem warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR 
@rem PURPOSE. TOC Inc assumes no liability or responsibilty for 
@rem the use of this information for any purpose.
@rem
@rem ************************************************************/

@rem ######################################################
@rem 
@rem  This script uses the following environment variables:
@rem   COREDX_TOP
@rem   COREDX_HOST
@rem   COREDX_TARGET
@rem  These variables can be set manually, or determined by
@rem  running the script  coredx/scripts/cdxenv.sh or cdxenv.bat
@rem
@rem  Note: javac must be accessible from the current PATH
@rem  Note: we use the 'ddl' file from ../hello_c/
@rem 
@rem %COREDX_TOP%\host\%COREDX_HOST%\bin\coredx_ddl.exe -l java -f Topics.ddl
@rem ######################################################

set SRC=MonitorWindow.java ContextDataReaderListener.java ContextMonitor.java TrafficInspectorMainWindow.java DatabaseModel.java TruckModel.java GatewayModel.java
set GEN_SRC=ConnectionReportTopic.java ConnectionReportTopicDataReader.java ConnectionReportTopicDataWriter.java ConnectionReportTopicSeq.java ConnectionReportTopicTypeSupport.java LoadReportTopic.java LoadReportTopicDataReader.java LoadReportTopicDataWriter.java LoadReportTopicSeq.java LoadReportTopicTypeSupport.java MessageStructureTopic.java MessageStructureTopicDataReader.java MessageStructureTopicDataWriter.java MessageStructureTopicSeq.java MessageStructureTopicTypeSupport.java PingTopic.java PingTopicDataReader.java PingTopicDataWriter.java PingTopicSeq.java PingTopicTypeSupport.java PrivateMessageTopic.java PrivateMessageTopicDataReader.java PrivateMessageTopicDataWriter.java PrivateMessageTopicSeq.java PrivateMessageTopicTypeSupport.java SubTest.java TruckGroupInfoTopic.java TruckGroupInfoTopicDataReader.java TruckGroupInfoTopicDataWriter.java TruckGroupInfoTopicSeq.java TruckGroupInfoTopicTypeSupport.java TruckInformationTopic.java TruckInformationTopicDataReader.java TruckInformationTopicDataWriter.java TruckInformationTopicSeq.java TruckInformationTopicTypeSupport.java TruckTrackingInformationTopic.java TruckTrackingInformationTopicDataReader.java TruckTrackingInformationTopicDataWriter.java TruckTrackingInformationTopicSeq.java TruckTrackingInformationTopicTypeSupport.java UnsentMessageTopic.java UnsentMessageTopicDataReader.java UnsentMessageTopicDataWriter.java UnsentMessageTopicSeq.java UnsentMessageTopicTypeSupport.java 

mkdir classes
javac -d classes -cp classes -cp %COREDX_TOP%\target\java\coredx_dds.jar %SRC% %GEN_SRC%

