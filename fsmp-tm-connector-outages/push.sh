#!/bin/bash
set -e 
	

INSTANCE_COUNT=${1:-"1"}
echo "Instance count: "$INSTANCE_COUNT

# The name of the application
APP_NAME=tm-outages
SPACE_NAME=$(cf target | grep Space: | cut -c 17-)


function checkAppExists() {
    RESULT=$(cf apps | grep $1)
}

function checkServiceExists() {
    RESULT=$(cf services | grep $1)
}


#checkServiceExists tm-connector-outages-db
#if [[ $RESULT != tm-connector-outages-db* ]]
#then echo "[ERROR] tm-connector-outages-db does not exist, creating..." && cf create-service postgres shared-nr tm-connector-outages-db;
#else echo "[INFO] tm-connector-outages-db does exist";
#fi

#get version
if [ -z "$VERSION" ]; then
    VERSION=$(mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version |grep -Ev '(^\[|Download\w+:)')
fi
VERSION=$(echo $VERSION  | tr '.' '_')

echo "Pushing application..."
cf pz $APP_NAME $VERSION -n $APP_NAME-$SPACE_NAME -i $INSTANCE_COUNT  --prestart "cf set-env $APP_NAME-$VERSION NEW_RELIC_APP_NAME $APP_NAME-$SPACE_NAME" --complete "curl -v -X PUT https://{FINAL_URL}/service/v1/platform/register"

checkAppExists $APP_NAME-inactive
if [[ $RESULT == $APP_NAME-inactive* ]]
then cf delete -f $APP_NAME-inactive;
fi

cf delete-orphaned-routes -f;

