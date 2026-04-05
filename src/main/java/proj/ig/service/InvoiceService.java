package proj.ig.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proj.ig.entity.User;
import proj.ig.repos.UserRepository;

@Service
public class InvoiceService {

    @Autowired private UserRepository userRepository;
    
    @Transactional
    public void finalizeInvoice(User user, InvoiceRequest invoiceData) {
        int currentNum = Integer.parseInt(invoiceData.getInvoiceNumber());
        user.setLastInvoiceNumber(currentNum);
        userRepository.save(user);
    }
}