curl --header "Content-Type: application/json" -X POST --data '{"link_id":"1","dm2_id":"a","dm3_id":"b"}' localhost:8000/produce/dm1
curl --header "Content-Type: application/json" -X POST --data '{"link_id":"1","dm2_id":"a","dm3_id":"b"}' localhost:8000/produce/dm2

curl -v localhost:8000/produce/dm1