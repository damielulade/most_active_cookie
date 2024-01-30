#!/bin/bash
gradle clean assemble
java -jar ./app/build/libs/app.jar $1 $2 $3 $4
gradle clean


# java -jar executable.jar &      # You send it in background
# MyPID=$!                        # You sign it's PID
# echo $MyPID                     # You print to terminal
# echo "kill $MyPID" > MyStop.sh  # Write the the command kill pid in MyStop.sh