package com.doriv.api_company.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doriv.api_company.models.Testt;
import com.doriv.api_company.repo.TestRepo;

@Service
public class TestService {
	
	@Autowired
	private TestRepo repo;
	
	private List<Testt> tests = new ArrayList<>(Arrays.asList(new Testt(1), new Testt(2), new Testt(3)));

	public List<Testt> getEveryTest() {
		List<Testt> tests = new ArrayList<>();
		repo.findAll().forEach(tests::add);
		return tests;
	}

	public Testt getTest(UUID id) {
//		return tests.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		return repo.findById(id).get();
	}

	public void addTest(Testt test) {
//		tests.add(test);
		repo.save(test);
	}

	public void updateTest(UUID id, Testt test) {
		repo.save(test);
//		for (int i = 0; i < tests.size(); i++) {
//			Testt t = tests.get(i);
//			if (t.getId().equals(id)) {
//				tests.set(i, test);
//				return;
//			}
//		}
	}

	public void dropTest(UUID id) {
		repo.deleteById(id);
//		tests.removeIf(t -> t.getId().equals(id));
	}
}
