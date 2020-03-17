package com.atcud.icecreamapp.repositories;

import java.util.List;

import com.atcud.icecreamapp.entities.FAQ;

public interface IFAQRepository {

	public List<FAQ> getAllFAQ();
	
	public FAQ getFAQ(int id);
	
	public void createFAQ(FAQ newFAQ);
	
	public void deleteFAQ(int id);
	
	public void updateFAQ(FAQ newFAQ);
	
}
