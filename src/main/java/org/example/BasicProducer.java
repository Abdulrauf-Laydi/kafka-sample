package org.example;



import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Properties;

public class BasicProducer {

    public static void main(String[] args) {
        System.out.println("*** Starting Basic Producer ***");

        Properties props = new Properties();
        props.put("bootstrap.servers", "<35.188.96.81>:9092");//the IP of your machine
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            // Create a Student object
            student student = new student(1, "John Doe");

            // Convert the Student object to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String studentJson = objectMapper.writeValueAsString(student);

            // Publish the JSON to Kafka
            producer.send(new ProducerRecord<>("student-topic", "student1", studentJson));
            System.out.println("Sent: " + studentJson);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}

class Student {
    private int id;
    private String name;

    // Constructor
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters (if needed)
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}


