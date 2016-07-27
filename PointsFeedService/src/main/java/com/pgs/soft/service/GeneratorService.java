package com.pgs.soft.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pgs.soft.domain.Message;
import com.pgs.soft.repository.CardRepository;

@Service
public class GeneratorService {

	RestTemplate restTemplate = new RestTemplate();
	Random rand = new Random();

	@Autowired
	CardRepository cardRepository;

	@Scheduled(fixedRate = 1000)
	public void send() {

		List<Integer> allCardsIds = StreamSupport.stream(cardRepository.findAll().spliterator(), false)
				.map(c -> c.getId()).collect(Collectors.toList());

		System.out.println("Sending");
		String result = restTemplate.postForObject("http://localhost:8080/new_points",
				new Message(allCardsIds.get(rand.nextInt(allCardsIds.size())), rand.nextInt(1901) + 100), String.class);

		System.out.println(result);
	}

}
