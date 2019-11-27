package kafka.stream;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StreamOperation {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public void getStream() {
		Properties streamsConfiguration = new Properties();
		streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "StreamApp");
		// streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,
		// "127.0.0.1:9092");
		streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,
				"b-2.cfkafkapoc.nj0tf5.c7.kafka.us-east-1.amazonaws.com:9094,b-1.cfkafkapoc.nj0tf5.c7.kafka.us-east-1.amazonaws.com:9094");
		streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		
		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, String> stream = builder.stream("fullfillment.public.testtable");
		logger.info("Received String");
		// KStream<String, String> concatstr = stream.flatMapValues(value ->
		// Arrays.asList(value.concat("infyAWS")));
		KStream<String, String> concatstr = stream.flatMapValues(value -> Arrays.asList(value.toUpperCase()));
		concatstr.to("connect-neel");
		logger.info("sent String");
		KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration); // Starting kafka stream
		streams.start();
	}
}
