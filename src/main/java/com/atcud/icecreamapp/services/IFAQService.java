package com.atcud.icecreamapp.services;

import java.util.List;

import com.atcud.icecreamapp.entities.FAQ;

public interface IFAQService {

	public List<FAQ> getAllFAQ();
	
	public void createFAQ(FAQ newFAQ);
	
	public void updateFAQ(FAQ newFAQ);
	
	public void deleteFAQ(int id);
	
}
