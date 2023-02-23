printenv

cd /tmp

java -jar -Dserver.port=6879 -Dserver.baseurl=${MOCK_API_BASE_URL} app.war run configure
