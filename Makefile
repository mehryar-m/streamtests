direction:
	echo "use make start_confluent, stop_confluent, start_jaeger, or stop_jaeger"

start_confluent:
	confluent local start --path /Users/mehryaribm/Documents/pnc/confluent-5.5.0
	docker run -d --name jaeger \
             -e COLLECTOR_ZIPKIN_HTTP_PORT=9411 \
             -p 5775:5775/udp \
             -p 6831:6831/udp \
             -p 6832:6832/udp \
             -p 5778:5778 \
             -p 16686:16686 \
             -p 14268:14268 \
             -p 14250:14250 \
             -p 9411:9411 \
             jaegertracing/all-in-one:1.18

create_topics_complex_state:
	~/Documents/pnc/kafka_2.12-2.3.0/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 \
		--replication-factor 1 --partitions 2 --topic Account
	~/Documents/pnc/kafka_2.12-2.3.0/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 \
		--replication-factor 1 --partitions 2 --topic Balance
	~/Documents/pnc/kafka_2.12-2.3.0/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 \
		--replication-factor 1 --partitions 2 --topic Customer
	~/Documents/pnc/kafka_2.12-2.3.0/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 \
		--replication-factor 1 --partitions 2 --topic CustomerRelationship

register_complex_state_schemas:
## TODO: Need to add the schema stuff here.

clean:
	confluent local stop --path /Users/mehryaribm/Documents/pnc/confluent-5.5.0
	docker stop jaeger
	docker rm jaeger
