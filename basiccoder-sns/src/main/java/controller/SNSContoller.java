package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;

@RestController
public class SNSContoller {
 
	@Autowired
	public AmazonSNSClient amazonSNSClient;
	
	public String TOPIC_ARN = "arn:aws:sns:ap-south-1:172118919561:sns-demo-topic";
	
	@GetMapping("/subscribe/{email}")
	public String subscribeToSNSTopic(@PathVariable("email")String email) {
		SubscribeRequest subscribeRequest = 
				new SubscribeRequest(TOPIC_ARN,"email",email);
		
		amazonSNSClient.subscribe(subscribeRequest);
		return "Check Your Email";
	}
	
	@GetMapping("/publish/{msg}")
	public String publishToTopic(@PathVariable("msg") String msg){
		PublishRequest publishRequest = 
				new PublishRequest(TOPIC_ARN,msg,"SNS Springboot");
		
		amazonSNSClient.publish(publishRequest);
		return "Message Published";
	}
}
