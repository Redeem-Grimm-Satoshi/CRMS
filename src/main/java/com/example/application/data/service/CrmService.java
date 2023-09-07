package com.example.application.data.service;

import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;
import com.example.application.data.repository.CompanyRepository;
import com.example.application.data.repository.ContactRepository;
import com.example.application.data.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CrmService {

    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;

    public CrmService(ContactRepository contactRepository, CompanyRepository companyRepository, StatusRepository statusRepository){
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
    }

    public List<Contact> findAllContact(String filterText){
        if(filterText==null || filterText.isEmpty()){
            return contactRepository.findAll();
        }else{
            return contactRepository.search(filterText);

        }

    }

    public long countContact(){
        return contactRepository.count();
    }

    public void deleteContact(Contact contact){
         contactRepository.delete(contact);

    }


    public void saveContact(Contact contact){
        if (contact==null){
            System.out.println("Contact cannot be null");
            return;
        }
        contactRepository.save(contact);

    }

    public List<Company> findCompany(){
        return companyRepository.findAll();
    }
    public List<Status> findStatus(){
        return statusRepository.findAll();
    }


}
